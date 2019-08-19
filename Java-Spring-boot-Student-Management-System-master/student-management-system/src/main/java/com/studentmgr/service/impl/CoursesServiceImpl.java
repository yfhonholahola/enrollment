package com.studentmgr.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmgr.common.service.impl.GenericServiceImpl;
import com.studentmgr.dao.CoursesDao;
import com.studentmgr.model.Courses;
import com.studentmgr.service.CoursesService;

@Service
public class CoursesServiceImpl extends GenericServiceImpl<Courses> implements CoursesService {

	@Autowired
	protected CoursesDao coursesDao;
	
	@PostConstruct
	void init() {
		init(Courses.class, coursesDao);
	}
}
