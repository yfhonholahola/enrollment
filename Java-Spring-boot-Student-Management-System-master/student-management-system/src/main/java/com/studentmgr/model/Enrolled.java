package com.studentmgr.model;

import java.util.Date;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

public class Enrolled {

	@Field("student")
	private Students student;
	
	@Field("enroldate")
	@Indexed
	private Date enrolDate;

	public Enrolled() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enrolled(Students student, Date enrolDate) {
		super();
		this.student = student;
		this.enrolDate = enrolDate;
	}

	public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}

	public Date getEnrolDate() {
		return enrolDate;
	}

	public void setEnrolDate(Date enrolDate) {
		this.enrolDate = enrolDate;
	}

	@Override
	public String toString() {
		return "Enrolled [student=" + student.toString() + ", enrolDate=" + enrolDate + "]";
	}
	
	
}
