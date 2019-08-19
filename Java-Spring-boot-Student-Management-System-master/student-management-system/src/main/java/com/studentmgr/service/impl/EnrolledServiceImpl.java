package com.studentmgr.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmgr.common.service.impl.GenericServiceImpl;
import com.studentmgr.dao.EnrolledDao;
import com.studentmgr.model.Enrolled;
import com.studentmgr.service.EnrolledService;

@Service
public class EnrolledServiceImpl extends GenericServiceImpl<Enrolled> implements EnrolledService {
	
	@Autowired
	protected EnrolledDao enrolledDao;
	
	@PostConstruct
	void init() {
		init(Enrolled.class, enrolledDao);
	}

}
