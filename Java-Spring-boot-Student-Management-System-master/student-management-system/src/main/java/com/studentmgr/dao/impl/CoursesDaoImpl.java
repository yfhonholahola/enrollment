package com.studentmgr.dao.impl;

import org.springframework.stereotype.Repository;

import com.studentmgr.common.dao.impl.GenericDaoImpl;
import com.studentmgr.dao.CoursesDao;
import com.studentmgr.model.Courses;

@Repository
public class CoursesDaoImpl extends GenericDaoImpl<Courses> implements CoursesDao {

	public CoursesDaoImpl() {
		super(Courses.class);
	}

}
