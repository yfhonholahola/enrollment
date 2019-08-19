package com.studentmgr.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmgr.common.service.impl.GenericServiceImpl;
import com.studentmgr.dao.DepartmentsDao;
import com.studentmgr.model.Departments;
import com.studentmgr.service.DepartmentsService;

@Service
public class DepartmentsServiceImpl extends GenericServiceImpl<Departments> implements DepartmentsService {

	@Autowired
	protected DepartmentsDao departmentsDao;
	
	@PostConstruct
	void init() {
		init(Departments.class, departmentsDao);
	}
}
