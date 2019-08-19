package com.studentmgr.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.studentmgr.common.model.EntityBase;

@Document(collection = "Offer")
public class Offer extends EntityBase {
	
	@DBRef
	private Departments department;
	
	@DBRef
	private Courses course;
	
	private int year;
	
	private int classSize;
	
	private int availablePlaces;

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	public Courses getCourse() {
		return course;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getClassSize() {
		return classSize;
	}

	public void setClassSize(int classSize) {
		this.classSize = classSize;
	}

	public int getAvailablePlaces() {
		return availablePlaces;
	}

	public void setAvailablePlaces(int availablePlaces) {
		this.availablePlaces = availablePlaces;
	}
	
	
}
