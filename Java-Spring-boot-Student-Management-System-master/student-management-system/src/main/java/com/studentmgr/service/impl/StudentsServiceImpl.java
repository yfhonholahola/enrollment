package com.studentmgr.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmgr.common.service.impl.GenericServiceImpl;
import com.studentmgr.dao.StudentsDao;
import com.studentmgr.model.Students;
import com.studentmgr.service.StudentsService;

@Service
public class StudentsServiceImpl extends GenericServiceImpl<Students> implements StudentsService {

	@Autowired
	protected StudentsDao studentsDao;
	
	@PostConstruct
	void init() {
		init(Students.class, studentsDao);
	}
}
