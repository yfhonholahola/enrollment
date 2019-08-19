package com.studentmgr.dao.impl;

import org.springframework.stereotype.Repository;

import com.studentmgr.common.dao.impl.GenericDaoImpl;
import com.studentmgr.dao.DepartmentsDao;
import com.studentmgr.model.Departments;

@Repository
public class DepartmentsDaoImpl extends GenericDaoImpl<Departments> implements DepartmentsDao {

	public DepartmentsDaoImpl() {
		super(Departments.class);
	}

}
