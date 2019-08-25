package com.studentmgr.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmgr.common.exception.DataAccessException;
import com.studentmgr.common.exception.ServiceException;
import com.studentmgr.common.service.impl.GenericServiceImpl;
import com.studentmgr.dao.CoursesDao;
import com.studentmgr.model.Courses;
import com.studentmgr.model.Departments;
import com.studentmgr.model.Students;
import com.studentmgr.service.CoursesService;

@Service
public class CoursesServiceImpl extends GenericServiceImpl<Courses> implements CoursesService {

	@Autowired
	protected CoursesDao coursesDao;
	
	@PostConstruct
	void init() {
		init(Courses.class, coursesDao);
	}

	@Override
	public List<String> getTitleByDeptYear(Departments dept, int year) throws ServiceException {
		try{
			return coursesDao.getTitleByDeptYear(dept, year);
		}catch(DataAccessException de){
			throw translateException(de);
		}		
	}

	@Override
	public List<Courses> getCourseByDeptsYear(List<Departments> depts, int year) throws ServiceException {
		try{
			return coursesDao.getCourseByDeptsYear(depts, year);
		}catch(DataAccessException de){
			throw translateException(de);
		}
	}

	@Override
	public Courses getPopularCourse(int mostOrLeast) throws ServiceException {
		try{
			return coursesDao.getPopularCourse(mostOrLeast);
		}catch(DataAccessException de){
			throw translateException(de);
		}
	}

	@Override
	public List<Courses> getCourseCountByDeptYear(Departments dept, int year) throws ServiceException {
		try{
			return coursesDao.getCourseCountByDeptYear(dept, year);
		}catch(DataAccessException de){
			throw translateException(de);
		}
	}

	@Override
	public List<Courses> getCourseByDeptStudentYear(Departments dept, Students student, int year)
			throws ServiceException {
		try{
			return coursesDao.getCourseByDeptStudentYear(dept, student, year);
		}catch(DataAccessException de){
			throw translateException(de);
		}
	}
}
