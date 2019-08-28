package com.studentmgr.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.StringUtils;

import com.studentmgr.common.model.EntityBase;

@Document(collection = "Courses")
public class Courses extends EntityBase implements Serializable  {
	@Field("courseid")
	private String courseID;
	
	@Field("title")
	private String title;
	
	@Field("level")
	private String level;
	
	@Field("department")
	private Departments department;
	
	@Field("year")
	@Indexed
	private int year;
	
	@Field("classsize")
	private int classSize;
	
	@Field("availableplaces")
	private int availablePlaces;
	
	@Field("enrolled")
	private List<Enrolled> enrolled;
	
	@Transient
	private int enrolledCount;
	
	public Courses() {
		super();
		this.courseID = "";
		this.title = "";
		this.level = "";
		this.department = new Departments();
		this.year = 0;
		this.classSize = 0;
		this.availablePlaces = 0;
		this.enrolled = new ArrayList<Enrolled>();
		this.enrolledCount = 0;
	}

	public Courses(String courseID, String title, String level, Departments department, int year, int classSize,
			int availablePlaces, List<Enrolled> enrolled) {
		super();
		this.courseID = courseID;
		this.title = title;
		this.level = level;
		this.department = department;
		this.year = year;
		this.classSize = classSize;
		this.availablePlaces = availablePlaces;
		this.enrolled = enrolled;
		this.enrolledCount = enrolledCount;
	}
	
	public Courses(String id, String courseID, String title, String level, Departments department, int year, int classSize,
			int availablePlaces, List<Enrolled> enrolled) {
		super();
		super.setId(id);
		this.courseID = courseID;
		this.title = title;
		this.level = level;
		this.department = department;
		this.year = year;
		this.classSize = classSize;
		this.availablePlaces = availablePlaces;
		this.enrolled = enrolled;
		this.enrolledCount = enrolledCount;
	}	

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
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

	public List<Enrolled> getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(List<Enrolled> enrolled) {
		this.enrolled = enrolled;
	}

	public int getEnrolledCount() {
		return enrolledCount;
	}

	public void setEnrolledCount(int enrolledCount) {
		this.enrolledCount = enrolledCount;
	}

	@Override
	public String toString() {
		return "Courses [courseID=" + courseID + ", title=" + title + ", level=" + level + ", department=" + department.toString()
				+ ", year=" + year + ", classSize=" + classSize + ", availablePlaces=" + availablePlaces + ", enrolled="
				+ StringUtils.arrayToCommaDelimitedString(enrolled.toArray()) + ", enrolledCount=" + enrolledCount + "]";
	}	
}
