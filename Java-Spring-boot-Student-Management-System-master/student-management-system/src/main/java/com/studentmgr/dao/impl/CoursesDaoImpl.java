package com.studentmgr.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoDatabase;
import com.studentmgr.common.dao.impl.GenericDaoImpl;
import com.studentmgr.common.exception.DataAccessException;
import com.studentmgr.dao.CoursesDao;
import com.studentmgr.model.Courses;
import com.studentmgr.model.Departments;
import com.studentmgr.model.Enrolled;
import com.studentmgr.model.Students;

@Repository
public class CoursesDaoImpl extends GenericDaoImpl<Courses> implements CoursesDao {

	private static final Logger logger = LoggerFactory.getLogger(CoursesDaoImpl.class);
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public CoursesDaoImpl() {
		super(Courses.class);
	}

	@Override
	@Cacheable
	public List<String> getTitleByDeptYear(Departments dept, int year) throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("getTitleByDeptYear");
		
		List<String> result = new ArrayList<String>();
		
		try {
			Query query = (dept != null && year != 0) ? new Query(Criteria.where("department.deptname").is(dept.getDeptName()).and("year").is(year)) : new Query();
			query.fields().include("title").exclude("_id");		
			
			List<DBObject> list = super.mongoOperations.find(query, DBObject.class, Courses.class.getSimpleName());				
			
			for (DBObject obj : list) { result.add(obj.get("title").toString()); }
			
			return result;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}		
	}

	@Override
	public List<Courses> getCourseByDeptsYear(List<Departments> depts, int year) throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("getTitleByDeptYear");
		
		try {
			Query query = (depts != null && depts.size() > 0 && year != 0) ? new Query(Criteria.where("department.deptname").in(depts.stream().map(Departments::getDeptName).toArray()).and("year").is(year)) : new Query();
			query.with(new Sort(Sort.Direction.ASC, "title"));
			query.limit(1000);
			
			return super.mongoOperations.find(query, Courses.class);						
		} catch (Exception e) {
			throw new DataAccessException(e);
		}		
	}

	/*
	 * mostOrLeast - most:-1, least:1
	 */
	@Override
	public Courses getPopularCourse(int mostOrLeast) throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("getPopularCourse");	
		
		Courses course = new Courses();
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db;
		
		try {
			db = mongoClient.getDatabase("courses");
			
			if (mostOrLeast == 1 || mostOrLeast == -1) {
				Document result = db.getCollection(Courses.class.getSimpleName()).aggregate(Arrays.asList(
						new Document("$addFields", new Document("enrolledcount", new Document("$size", "$enrolled"))),
						new Document("$sort", new Document("enrolledcount", mostOrLeast)),
						new Document("$limit", 1)
						)).first();
				
				List<Document> enrolleddocs = (List<Document>)result.get("enrolled");
				List<Enrolled> enrolleds = new ArrayList<Enrolled>();
				for (Document enrolleddoc : enrolleddocs) {
					Document studentdoc = enrolleddoc.get("student", Document.class);
					enrolleds.add(new Enrolled(new Students(new BigDecimal(studentdoc.getString("studentid")), studentdoc.getString("stuname"), studentdoc.getDate("dob")), enrolleddoc.getDate("enroldate")));
				}
				
				Document deptDoc = result.get("department", Document.class);
				Departments dept = new Departments(deptDoc.getString("deptname"), deptDoc.getString("location"));
				
				course = new Courses(result.getObjectId("_id").toString(), result.getString("courseid"), result.getString("title"), result.getString("level"), dept, result.getInteger("year"), result.getInteger("classsize"),result.getInteger("availableplaces"),enrolleds);
				course.setEnrolledCount(result.getInteger("enrolledcount"));				
			}

		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			mongoClient.close();			
		}
		return course;
	}

	@Override
	public List<Courses> getCourseCountByDeptYear(Departments dept, int year) throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("getCourseCountByDeptYear");	
		
		List<Courses> courses = new ArrayList<Courses>();
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db;
		
		try {
			db = mongoClient.getDatabase("courses");
			
			if (dept != null && year != 0) {
				AggregateIterable<Document> result = db.getCollection(Courses.class.getSimpleName()).aggregate(Arrays.asList(
						new Document("$match", new Document("department.deptname", dept.getDeptName()).append("year", year)),
						new Document("$project", new Document("title", 1).append("enrolledcount", new Document("$size", "$enrolled")))
						));
				
				for(Document doc : result) {
					Courses item = new Courses();
					item.setTitle(doc.getString("title"));
					item.setEnrolledCount(doc.getInteger("enrolledcount"));
					courses.add(item);
				}
			}

			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			mongoClient.close();			
		}
		return courses;
	}

	@Override
	public List<Courses> getCourseByDeptStudentYear(Departments dept, Students student, int year)
			throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("getCourseByDeptStudentYear");
		
		try {
			Query query = (dept != null && year != 0) ? new Query(Criteria.where("enrolled").elemMatch(Criteria.where("student.studentid").is(student.getStudentID()).and("enroldate").gte(dateFormat.parse(String.format("%s-01-01 00:00:00", year))).lte(dateFormat.parse(String.format("%s-01-01 00:00:00", year+1))))) : new Query();
			query.with(new Sort(Sort.Direction.ASC, "title"));
			query.limit(1000);
			
			return super.mongoOperations.find(query, Courses.class);						
		} catch (Exception e) {
			throw new DataAccessException(e);
		}		
	}

}
