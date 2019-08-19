package com.studentmgr.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.studentmgr.common.model.EntityBase;

@Document(collection = "Enrolled")
public class Enrolled extends EntityBase {

	@DBRef
	private Students student;
	
	private int year;
	
	@DBRef
	private Courses course;
	
	private Date enrolDate;

	public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Courses getCourse() {
		return course;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}

	public Date getEnrolDate() {
		return enrolDate;
	}

	public void setEnrolDate(Date enrolDate) {
		this.enrolDate = enrolDate;
	}
	
	
}
