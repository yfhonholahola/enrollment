package com.studentmgr.utils.editors;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;

import com.studentmgr.model.Students;

public class StudentsEditor extends PropertyEditorSupport {

	public StudentsEditor() {
		// TODO Auto-generated constructor stub
	}

	public StudentsEditor(Object arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void setAsText(String text) throws IllegalArgumentException {		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] tokens = text.replace("Students [studentID=", "").replace(" stuName=", "").replace(" dOB=", "").replace("]", "").split(",");
		
        if (StringUtils.hasText(text)) {
        	try {
        		super.setValue(new Students(new BigDecimal(tokens[0]),tokens[1],dateFormat.parse(tokens[2])));	
        	} catch (Exception e) {        		
        	}        
        } else {
        	super.setValue(null);
        }		
	}
	
	

}
