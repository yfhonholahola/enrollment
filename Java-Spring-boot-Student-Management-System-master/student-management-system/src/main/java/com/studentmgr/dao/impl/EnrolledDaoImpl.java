package com.studentmgr.dao.impl;

import org.springframework.stereotype.Repository;

import com.studentmgr.common.dao.impl.GenericDaoImpl;
import com.studentmgr.dao.EnrolledDao;
import com.studentmgr.model.Enrolled;

@Repository
public class EnrolledDaoImpl extends GenericDaoImpl<Enrolled> implements EnrolledDao {

	public EnrolledDaoImpl() {
		super(Enrolled.class);
	}

}
