package com.studentmgr.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

public class Students {

	@Field("studentid")
	@Indexed
	private BigDecimal studentID;
	
	@Field("stuname")
	private String stuName;
	
	@Field("dob")
	private Date dOB;

	public Students() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Students(BigDecimal studentID, String stuName, Date dOB) {
		super();
		this.studentID = studentID;
		this.stuName = stuName;
		this.dOB = dOB;
	}

	public BigDecimal getStudentID() {
		return studentID;
	}

	public void setStudentID(BigDecimal studentID) {
		this.studentID = studentID;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public Date getdOB() {
		return dOB;
	}

	public void setdOB(Date dOB) {
		this.dOB = dOB;
	}

	@Override
	public String toString() {
		return "Students [studentID=" + studentID + ", stuName=" + stuName + ", dOB=" + dOB + "]";
	}
	
	
}
