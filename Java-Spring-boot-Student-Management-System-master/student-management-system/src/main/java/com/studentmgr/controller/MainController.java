package com.studentmgr.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.studentmgr.common.exception.ServiceException;
import com.studentmgr.model.Courses;
import com.studentmgr.model.Departments;
import com.studentmgr.model.Enrolled;
import com.studentmgr.model.Student;
import com.studentmgr.model.Students;
import com.studentmgr.service.CoursesService;
import com.studentmgr.service.StudentService;
import com.studentmgr.utils.StudentMgrUtils;
import com.studentmgr.utils.editors.DepartmentsEditor;

@Controller
public class MainController {

	private static final Logger LOG = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private CoursesService courseService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Departments.class, new DepartmentsEditor());
	}	
	
	
	
	@RequestMapping("/")
	public String rootPage(Model model){
		
		/*
		List<Student> students = new ArrayList<>();
		try{
			students = studentService.getAll();
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		
		model.addAttribute("students", students);
		*/
		
		return "main";
	}
	
	@RequestMapping("/main")
	public String mainPage(Model model){		
		return "main";
	}	
	
	@RequestMapping("/courseTitle")
	public String courseTitlePage(Model model){	
		if (!model.containsAttribute("result")) {
			List<String> result = new ArrayList<String>();
			model.addAttribute("filter", "");
			model.addAttribute("result", result);	
		}

		model.addAttribute("deptList", StudentMgrUtils.getDepartmentsWithEmpty());
		return "courseTitle";
	}
	
	@RequestMapping(value="/courseTitle", method=RequestMethod.POST)
	@GetMapping
	public String courseTitleSearch(@RequestParam(name="dept", defaultValue="")String deptName, @RequestParam(name="year", defaultValue="0")int year, Model model){				
		LOG.info("courseTitleSearch");
		
		List<String> result = new ArrayList<String>();
		
		try{
			Departments dept = StudentMgrUtils.departments.stream().filter(q -> deptName.equals(q.getDeptName())).findFirst().orElse(new Departments());
			result = courseService.getTitleByDeptYear(dept, year);
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		
		model.addAttribute("filter", String.format("%s-%s", deptName, year));
		model.addAttribute("result", result);
		
		return this.courseTitlePage(model);
	}		
		
	
	@RequestMapping("/courseInfo")
	public String courseInfoPage(Model model){
		if (!model.containsAttribute("result")) {
			List<Courses> result = new ArrayList<Courses>();
			model.addAttribute("filter", "");
			model.addAttribute("result", result);	
		}

		model.addAttribute("deptList", StudentMgrUtils.getDepartmentsWithEmpty());				
		return "courseInfo";
	}
	
	@RequestMapping(value="/courseInfo", method=RequestMethod.POST)
	public String courseInfoSearch(@RequestParam(name="dept", defaultValue="")String deptNames, @RequestParam(name="year", defaultValue="0")int year, Model model){
		LOG.info("courseInfoSearch");	
		
		List<Courses> result = new ArrayList<Courses>();		
		
		try{
			List<Departments> dept = StudentMgrUtils.departments.stream().filter(q -> Arrays.asList(deptNames.split(",")).contains(q.getDeptName())).collect(Collectors.toList());
			result = courseService.getCourseByDeptsYear(dept, year);
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		
		model.addAttribute("filter", String.format("%s-%s", deptNames, year));
		model.addAttribute("result", result);		
		
		return this.courseInfoPage(model);
	}	
	
	@RequestMapping("/popularCourse")
	public String popularCoursePage(Model model){			
		if (!model.containsAttribute("result")) {
			Courses result = new Courses();
			model.addAttribute("filter", "");
			model.addAttribute("result", result);	
		}
		
		model.addAttribute("popularity", StudentMgrUtils.popularity);
		return "popularCourse";
	}
	
	@RequestMapping(value="/popularCourse", method=RequestMethod.POST)
	public String popularCourseSearch(@RequestParam(name="pop", defaultValue="1")int pop, Model model){		
		LOG.info("popularCoursePage");
		
		Courses result = new Courses();		
		
		try{			
			result = courseService.getPopularCourse(pop);
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		
		model.addAttribute("filter", StudentMgrUtils.popularity.get(pop));
		model.addAttribute("result", result);		
		
		return this.popularCoursePage(model);
	}	
	
	@RequestMapping("/studentCount")
	public String studentCountPage(Model model){
		if (!model.containsAttribute("result")) {
			List<Courses> result = new ArrayList<Courses>();
			model.addAttribute("filter", "");
			model.addAttribute("result", result);	
		}

		model.addAttribute("deptList", StudentMgrUtils.getDepartmentsWithEmpty());
		
		return "studentCount";
	}
	
	@RequestMapping(value="/studentCount", method=RequestMethod.POST)
	public String studentCountSearch(@RequestParam(name="dept", defaultValue="")String deptName, @RequestParam(name="year", defaultValue="0")int year, Model model){
		LOG.info("studentCount");
		
		List<Courses> result = new ArrayList<Courses>();
		
		try{
			Departments dept = StudentMgrUtils.departments.stream().filter(q -> deptName.equals(q.getDeptName())).findFirst().orElse(new Departments());
			result = courseService.getCourseCountByDeptYear(dept, year);
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		
		model.addAttribute("filter", String.format("%s-%s", deptName, year));
		model.addAttribute("result", result);
		
		return this.studentCountPage(model);			
	}	
	
	@RequestMapping("/studentEnrollment")
	public String studentEnrollmentPage(Model model){		
		if (!model.containsAttribute("result")) {
			List<Courses> result = new ArrayList<Courses>();
			model.addAttribute("filter", "");
			model.addAttribute("result", result);	
		}

		model.addAttribute("stdList", StudentMgrUtils.getStudentsWithEmpty());
		model.addAttribute("deptList", StudentMgrUtils.getDepartmentsWithEmpty());
		
		return "studentEnrollment";
	}
	
	@RequestMapping(value="/studentEnrollment", method=RequestMethod.POST)
	public String studentEnrollmentSearch(@RequestParam(name="std", defaultValue="")String stdName, @RequestParam(name="dept", defaultValue="")String deptName, @RequestParam(name="year", defaultValue="0")int year, Model model){		
		LOG.info("studentEnrollment");	
		
		List<Courses> result = new ArrayList<Courses>();		
		
		try{
			Students std = StudentMgrUtils.students.stream().filter(q -> stdName.equals(q.getStuName())).findFirst().orElse(new Students());
			Departments dept = StudentMgrUtils.departments.stream().filter(q -> deptName.equals(q.getDeptName())).findFirst().orElse(new Departments());
			result = courseService.getCourseByDeptStudentYear(dept, std, year);
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		
		model.addAttribute("filter", String.format("%s-%s-%s", stdName, deptName, year));
		model.addAttribute("result", result);		
		
		return this.studentEnrollmentPage(model);			
	}	

	@RequestMapping("/insert")
	public String insertPage(Model model){	
		Courses course = new Courses();
		model.addAttribute("deptList", StudentMgrUtils.getDepartmentsWithEmpty());
		model.addAttribute("course", course);		
		return "insert";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insertCourse(@ModelAttribute Courses course, BindingResult result, Model model, final RedirectAttributes redirectAttributes){		
		LOG.info("insertCourse");	
		
		if (course != null) {
			try {
				course.setId(null);
				Courses rtn = courseService.add(course);
				if (rtn != null) {
					redirectAttributes.addFlashAttribute("css", "success");
					redirectAttributes.addFlashAttribute("msg", "Course added successfully!");
				}
				String rtnPage=this.insertPage(model);
				return "redirect:/"+rtnPage;
			}catch(Exception e){
				LOG.error(e.getMessage());
			}
		}
		
		return this.insertPage(model);
	}	
	
	@RequestMapping("/remove")
	public String removePage(Model model){		
	
		List<Courses> courses = new ArrayList<Courses>();
		
		try{
			courses = courseService.getAll();
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		
		model.addAttribute("courses", courses);	
	
		return "remove";
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String removeCourses(@RequestParam(name="course", defaultValue="")String courseId, Model model, final RedirectAttributes redirectAttributes){	
		LOG.info("removeCourses");	
		
		if (!courseId.isEmpty()) {
			try {
				boolean result = courseService.deleteById(courseId);
				
				if (result) {
					redirectAttributes.addFlashAttribute("css", "success");
					redirectAttributes.addFlashAttribute("msg", "Course removed successfully!");									
				}
				String rtnPage=this.removePage(model);
				return "redirect:/"+rtnPage;				
			}catch(Exception e){
				LOG.error(e.getMessage());
			}			
		}

		return this.removePage(model);
	}	

	@RequestMapping("/join")
	public String joinPage(Model model){		
		List<Courses> courses = new ArrayList<Courses>();
		
		try{
			courses = courseService.getAll();
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		
		model.addAttribute("crsList", courses);				
		model.addAttribute("stdList", StudentMgrUtils.students);
		
		return "update";
	}	
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinCourses(@RequestParam(name="course", defaultValue="")String courseId, @RequestParam(name="std", defaultValue="0")BigDecimal stdID, Model model, final RedirectAttributes redirectAttributes){
		LOG.info("updateCourses");		
		
		try{
			Courses courses = courseService.getById(courseId);
			
			boolean isStdExist = courses.getEnrolled().stream().anyMatch(q -> q.getStudent().getStudentID().equals(stdID));
			
			if (isStdExist) {
				redirectAttributes.addFlashAttribute("css", "warning");
				redirectAttributes.addFlashAttribute("msg", "Student already exist!");					
			} else {
				Students std = StudentMgrUtils.students.stream().filter(q -> stdID.equals(q.getStudentID())).findFirst().orElse(new Students());
				courses.getEnrolled().add(new Enrolled(std, new Date()));
				
				courseService.edit(courses);
				
				redirectAttributes.addFlashAttribute("css", "success");
				redirectAttributes.addFlashAttribute("msg", "Student join successfully!");
			}
		}catch(Exception e){
			LOG.error(e.getMessage());
		}		
		
		String rtnPage=this.joinPage(model);
		return "redirect:/"+rtnPage;	
	}	
	
	/*
	@RequestMapping("/save")
	public String savePage(@RequestParam(value="q", required=false) String id, Model model) throws ServiceException{
		
		Student student = null;
		if(!StringUtils.isEmpty(id))
			student = studentService.getById(id);
			
		model.addAttribute("genderList", Arrays.asList(Student.GENDER_MALE, Student.GENDER_FEMALE));
		model.addAttribute("student", student);
		return "save";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveBooking(@ModelAttribute Student student) throws ServiceException{
		
		if(StringUtils.isEmpty(student.getId())){
			student.setId(null);
			studentService.add(student);
		}
		else
			studentService.edit(student);
		
		return "redirect:/";
	}
	
	@RequestMapping("/delete")
	public String deleteBooking(@ModelAttribute Student student) throws ServiceException{
		studentService.delete(student);
		return "redirect:/";
	}
	*/
}
