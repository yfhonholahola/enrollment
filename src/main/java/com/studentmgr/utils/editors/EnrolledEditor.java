package com.studentmgr.utils.editors;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;

import com.studentmgr.model.Enrolled;

public class EnrolledEditor extends PropertyEditorSupport {

	public EnrolledEditor() {
		// TODO Auto-generated constructor stub
	}

	public EnrolledEditor(Object arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void setAsText(String text) throws IllegalArgumentException {		
	
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] tokens = text.replace("Enrolled [student=", "").replace(" enrolDate=", "").replace("]", "").split(",");
		
        if (StringUtils.hasText(text)) {
        	super.setValue(new Enrolled());
        } else {
        	super.setValue(null);
        }		
	}
	
	

}
