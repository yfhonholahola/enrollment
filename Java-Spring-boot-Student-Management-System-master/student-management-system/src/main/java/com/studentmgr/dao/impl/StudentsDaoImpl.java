package com.studentmgr.dao.impl;

import org.springframework.stereotype.Repository;

import com.studentmgr.common.dao.impl.GenericDaoImpl;
import com.studentmgr.dao.StudentsDao;
import com.studentmgr.model.Students;

@Repository
public class StudentsDaoImpl extends GenericDaoImpl<Students> implements StudentsDao {

	public StudentsDaoImpl() {
		super(Students.class);
	}

}
