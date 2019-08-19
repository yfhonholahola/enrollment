package com.studentmgr.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.studentmgr.common.model.EntityBase;

@Document(collection = "Departments")
public class Departments extends EntityBase {
	private String deptID;
	
	private String deptName;
	
	private String location;

	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
