package com.studentmgr.dao;

import java.util.List;

import com.studentmgr.common.dao.GenericDao;
import com.studentmgr.common.exception.DataAccessException;
import com.studentmgr.model.Courses;
import com.studentmgr.model.Departments;
import com.studentmgr.model.Students;

public interface CoursesDao extends GenericDao<Courses> {
	List<String> getTitleByDeptYear(Departments dept, int year) throws DataAccessException;
	
	List<Courses> getCourseByDeptsYear(List<Departments> depts, int year) throws DataAccessException;
	
	Courses getPopularCourse(int mostOrLeast) throws DataAccessException;
	
	List<Courses> getCourseCountByDeptYear(Departments dept, int year) throws DataAccessException;
	
	List<Courses> getCourseByDeptStudentYear(Departments dept, Students student, int year) throws DataAccessException;
}
