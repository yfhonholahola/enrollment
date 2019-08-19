package com.studentmgr.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.studentmgr.common.model.EntityBase;

@Document(collection = "Courses")
public class Courses extends EntityBase {

	private String courseID;
	
	private String title;
	
	private String level;

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
	
	
}
