package com.studentmgr.model;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

import com.studentmgr.common.model.EntityBase;

@Document(collection = "Students")
public class Students extends EntityBase {

	private BigDecimal studentID;
	
	private String stuName;
	
	private String dOB;

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

	public String getdOB() {
		return dOB;
	}

	public void setdOB(String dOB) {
		this.dOB = dOB;
	}
	
	
}
