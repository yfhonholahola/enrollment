package com.studentmgr.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

public class Departments {
	
	@Field("deptname")
	@Indexed
	private String deptName;
	
	@Field("location")
	private String location;

	public Departments() {
		super();
		this.deptName = "";
		this.location = "";
	}

	public Departments(String deptName, String location) {
		super();
		this.deptName = deptName;
		this.location = location;
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

	@Override
	public String toString() {
		return "Departments [deptName=" + deptName + ", location=" + location + "]";
	}
	
	
}
