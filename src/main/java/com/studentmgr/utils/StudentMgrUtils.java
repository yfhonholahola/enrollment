package com.studentmgr.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.studentmgr.model.Departments;
import com.studentmgr.model.Students;

public final class StudentMgrUtils {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static final Departments deptCS = new Departments("CS", "Kowloon Tong");
	public static final Departments deptIS = new Departments("IS", "Kowloon Tong");
	public static final Departments deptBIT = new Departments("BIT", "Tsin Shai Shui");
		
	public static final Students stdA = new Students(new BigDecimal(100001),"Chan Tai Man", safeParse("1998-01-01 12:53:59"));
	public static final Students stdB = new Students(new BigDecimal(100002),"Lee Chi Hei", safeParse("1998-06-12 23:01:18"));    
	public static final Students stdC = new Students(new BigDecimal(100003),"Wong Mei Ki", safeParse("1997-12-13 06:23:08"));    
	public static final Students stdD = new Students(new BigDecimal(100004),"Ng Chi Ko", safeParse("1999-03-16 19:13:48"));      
	public static final Students stdE = new Students(new BigDecimal(100005),"Chan Wai On", safeParse("1997-01-22 20:00:05"));    
	public static final Students stdF = new Students(new BigDecimal(100006),"Lee Ho Pui", safeParse("1996-02-18 14:35:22"));     
	public static final Students stdG = new Students(new BigDecimal(100007),"Sum O Chi", safeParse("1998-07-07 05:05:00"));      
	public static final Students stdH = new Students(new BigDecimal(100008),"Chan Siu Ming", safeParse("1996-01-30 04:20:12"));  
	public static final Students stdI = new Students(new BigDecimal(100009),"Cheung Kai Shek", safeParse("1999-11-21 12:11:00"));
	public static final Students stdJ = new Students(new BigDecimal(100010),"Shek On Yee", safeParse("1997-05-24 08:02:35"));	   
	
	public static final Integer mostPopular = -1;
	public static final Integer leastPopular = 1;
	
	public static final List<Departments> departments = Arrays.asList(deptCS,deptIS,deptBIT);
	public static final List<Students> students = Arrays.asList(stdA,stdB,stdC,stdD,stdE,stdF,stdG,stdH,stdI,stdJ);
	
	public static final Map<Integer, String> popularity;
	
	static {
			Map<Integer, String> tmp = new LinkedHashMap<Integer, String>() ;
			tmp.put(mostPopular, "Most Popular");
			tmp.put(leastPopular, "Least Popular");
			popularity = Collections.unmodifiableMap(tmp);
	}
	
	static Date safeParse(String input) {
	    try {
	        return dateFormat.parse(input);
	    } catch (ParseException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public static List<Departments> getDepartmentsWithEmpty() {
		return new ArrayList<Departments>(departments) {{
			add(0,new Departments());
		}};
	}
	
	public static List<Students> getStudentsWithEmpty() {
		return new ArrayList<Students>(students) {{
			add(0,new Students());
		}};
	}	
}