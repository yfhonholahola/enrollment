package com.studentmgr.utils.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

import com.studentmgr.model.Departments;

public class DepartmentsEditor extends PropertyEditorSupport {

	public DepartmentsEditor() {
		// TODO Auto-generated constructor stub
	}

	public DepartmentsEditor(Object arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void setAsText(String text) throws IllegalArgumentException {		
		String[] tokens = text.replace("Departments [deptName=", "").replace(" location=", "").replace("]", "").split(",");
		
        if (StringUtils.hasText(text)) {
        	super.setValue(new Departments(tokens[0],tokens[1]));
        } else {
        	super.setValue(null);
        }		
	}
	
	

}
