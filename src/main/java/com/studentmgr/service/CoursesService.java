package com.studentmgr.service;

import java.util.List;

import com.studentmgr.common.exception.ServiceException;
import com.studentmgr.common.service.GenericService;
import com.studentmgr.model.Courses;
import com.studentmgr.model.Departments;
import com.studentmgr.model.Students;

public interface CoursesService extends GenericService<Courses> {
	List<String> getTitleByDeptYear(Departments dept, int year) throws ServiceException;
	
	List<Courses> getCourseByDeptsYear(List<Departments> depts, int year) throws ServiceException;
	
	Courses getPopularCourse(int mostOrLeast) throws ServiceException;
	
	List<Courses> getCourseCountByDeptYear(Departments dept, int year) throws ServiceException;
	
	List<Courses> getCourseByDeptStudentYear(Departments dept, Students student, int year) throws ServiceException;
}
