package com.studentmgr.config;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.studentmgr.common.exception.ServiceException;
import com.studentmgr.model.Courses;
import com.studentmgr.model.Departments;
import com.studentmgr.model.Enrolled;
import com.studentmgr.model.Students;
import com.studentmgr.service.CoursesService;

@Component
public class SetupCourses {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	public SetupCourses(CoursesService courseService) {
		// TODO Auto-generated constructor stub
		try {
			if (courseService.count() == 0) {
				Departments deptCS = new Departments("CS", "Kowloon Tong");
				Departments deptIS = new Departments("IS", "Kowloon Tong");
				Departments deptBIT = new Departments("BIT", "Tsin Shai Shui");
				
				Students stdA = new Students(new BigDecimal(100001),"Chan Tai Man", dateFormat.parse("1998-01-01 12:53:59"));
				Students stdB = new Students(new BigDecimal(100002),"Lee Chi Hei", dateFormat.parse("1998-06-12 23:01:18"));
				Students stdC = new Students(new BigDecimal(100003),"Wong Mei Ki", dateFormat.parse("1997-12-13 06:23:08"));
				Students stdD = new Students(new BigDecimal(100004),"Ng Chi Ko", dateFormat.parse("1999-03-16 19:13:48"));
				Students stdE = new Students(new BigDecimal(100005),"Chan Wai On", dateFormat.parse("1997-01-22 20:00:05"));
				Students stdF = new Students(new BigDecimal(100006),"Lee Ho Pui", dateFormat.parse("1996-02-18 14:35:22"));
				Students stdG = new Students(new BigDecimal(100007),"Sum O Chi", dateFormat.parse("1998-07-07 05:05:00"));
				Students stdH = new Students(new BigDecimal(100008),"Chan Siu Ming", dateFormat.parse("1996-01-30 04:20:12"));
				Students stdI = new Students(new BigDecimal(100009),"Cheung Kai Shek", dateFormat.parse("1999-11-21 12:11:00"));
				Students stdJ = new Students(new BigDecimal(100010),"Shek On Yee", dateFormat.parse("1997-05-24 08:02:35"));
				
				/*
				Courses courseADD = new Courses(
						"ADD",
						"APPLICATION DESIGN AND DEVELOPMENT",
						"1",
						deptCS,
						2016,
						4,
						0,
						Arrays.asList(
								new Enrolled(stdA, dateFormat.parse("2018-09-01 00:00:00")),
								new Enrolled(stdB, dateFormat.parse("2018-09-01 00:00:00")),
								new Enrolled(stdC, dateFormat.parse("2018-09-01 00:00:00")),
								new Enrolled(stdD, dateFormat.parse("2018-09-01 00:00:00"))
								)
						);
				*/
				
				//Sem1
				Courses courseADD = new Courses("ADD", "APPLICATION DESIGN AND DEVELOPMENT","1",deptCS,2016,8,0,Arrays.asList(new Enrolled(stdA, dateFormat.parse("2016-09-01 00:00:00")),new Enrolled(stdB, dateFormat.parse("2016-09-01 00:00:00")),new Enrolled(stdC, dateFormat.parse("2016-09-01 00:00:00")),new Enrolled(stdD, dateFormat.parse("2016-09-01 00:00:00")),new Enrolled(stdE, dateFormat.parse("2016-09-01 00:00:00")),new Enrolled(stdF, dateFormat.parse("2016-09-01 00:00:00")),new Enrolled(stdG, dateFormat.parse("2016-09-01 00:00:00")),new Enrolled(stdH, dateFormat.parse("2016-09-01 00:00:00"))));				
				Courses courseOS = new Courses("OS","OPERATING SYSTEMS","1",deptCS,2016,4,0,Arrays.asList(new Enrolled(stdA, dateFormat.parse("2016-09-01 00:00:00")),new Enrolled(stdB, dateFormat.parse("2016-09-01 00:00:00")),new Enrolled(stdC, dateFormat.parse("2016-09-01 00:00:00")),new Enrolled(stdD, dateFormat.parse("2016-09-01 00:00:00"))));
				Courses courseSISB = new Courses("SISB","Strategic Information Systems for Business","1",deptIS,2016,6,0,Arrays.asList(new Enrolled(stdE, dateFormat.parse("2016-09-01 00:00:00")),new Enrolled(stdF, dateFormat.parse("2016-09-01 00:00:00")),new Enrolled(stdG, dateFormat.parse("2016-09-01 00:00:00")),new Enrolled(stdH, dateFormat.parse("2016-09-01 00:00:00")),new Enrolled(stdI, dateFormat.parse("2016-09-01 00:00:00")),new Enrolled(stdJ, dateFormat.parse("2016-09-01 00:00:00"))));
				Courses courseEAD = new Courses("EAD","Enterprise Application Development","1",deptBIT,2016,2,0,Arrays.asList(new Enrolled(stdI, dateFormat.parse("2016-09-01 00:00:00")),new Enrolled(stdJ, dateFormat.parse("2016-09-01 00:00:00"))));
				
				//Sem2
				Courses coursePM = new Courses("PM","Project Management","2",deptBIT,2017,10,0,Arrays.asList(new Enrolled(stdA, dateFormat.parse("2016-12-22 00:00:00")),new Enrolled(stdB, dateFormat.parse("2016-12-22 00:00:00")),new Enrolled(stdC, dateFormat.parse("2016-12-22 00:00:00")),new Enrolled(stdD, dateFormat.parse("2016-12-22 00:00:00")),new Enrolled(stdE, dateFormat.parse("2016-12-22 00:00:00")),new Enrolled(stdF, dateFormat.parse("2016-12-22 00:00:00")),new Enrolled(stdG, dateFormat.parse("2016-12-22 00:00:00")),new Enrolled(stdH, dateFormat.parse("2016-12-22 00:00:00")),new Enrolled(stdI, dateFormat.parse("2016-12-22 00:00:00")),new Enrolled(stdJ, dateFormat.parse("2016-12-22 00:00:00"))));
				Courses courseNAP = new Courses("NAP","Networks And Protocols","2",deptCS,2017,4,0,Arrays.asList(new Enrolled(stdA, dateFormat.parse("2016-12-22 00:00:00")),new Enrolled(stdB, dateFormat.parse("2016-12-22 00:00:00")),new Enrolled(stdC, dateFormat.parse("2016-12-22 00:00:00")),new Enrolled(stdD, dateFormat.parse("2016-12-22 00:00:00"))));
				Courses courseISM = new Courses("ISM","Information Systems Management","2",deptIS,2017,4,0,Arrays.asList(new Enrolled(stdE, dateFormat.parse("2016-12-22 00:00:00")),new Enrolled(stdF, dateFormat.parse("2016-12-22 00:00:00")),new Enrolled(stdG, dateFormat.parse("2016-12-22 00:00:00")),new Enrolled(stdH, dateFormat.parse("2016-12-22 00:00:00"))));
				
				//Sem3
				Courses courseKDDM = new Courses("KDDM","Knowledge Discovery And Data Mining","3",deptIS,2018,10,9,Arrays.asList(new Enrolled(stdA, dateFormat.parse("2018-05-12 15:30:00"))));
				Courses courseADS = new Courses("ADS","Advanced Database Systems","3",deptCS,2018,8,7,Arrays.asList(new Enrolled(stdA, dateFormat.parse("2018-15-22 15:32:00"))));
				
				courseService.add(courseADD);
				courseService.add(courseOS);
				courseService.add(courseSISB);
				courseService.add(courseEAD);
				courseService.add(coursePM);
				courseService.add(courseNAP);
				courseService.add(courseISM);
				courseService.add(courseKDDM);
				courseService.add(courseADS);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ParseException e) {
		}	
	}

}
