package com.signify.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.exception.AddCourseException;
import com.signify.exception.CourseFoundException;
import com.signify.exception.CourseNotAssignedToProfessorException;
import com.signify.exception.CourseNotDeletedException;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.ProfessorNotAddedException;
import com.signify.exception.ProfessorNotFoundException;
import com.signify.exception.StudentNotFoundForVerificationException;
import com.signify.exception.StudentNotRegisteredException;
import com.signify.exception.UserAlreadyExistException;
import com.signify.service.AdminServiceOperation;

/**
 * @author HIMANSHU YADAV
 * The Class AdminRestControllerAPI.
 */
@RestController
public class AdminRestControllerAPI {

	/** The admin service. */
	@Autowired
	private AdminServiceOperation adminService;

	/**
	 * Approve all students.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/approveall", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> approveAllStudents() {
		adminService.approveAllStudents();
		return new ResponseEntity<String>("All Students Approved Successfully", HttpStatus.OK);
	}

	/**
	 * Approve student by id.
	 *
	 * @param data the data
	 * @return the response entity
	 */
	@RequestMapping(value = "/approvebyid", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> approveStudentById(@RequestBody Map<String, String> data) {
		try {
			adminService.approveStudentById(data.get("studentId"));
			return new ResponseEntity<String>("All Students Approved Successfully", HttpStatus.OK);
		} catch (StudentNotFoundForVerificationException e) {
			return new ResponseEntity<String>("Student Id Not Found", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Gets the unapproved students.
	 *
	 * @return the unapproved students
	 */
	@RequestMapping(value = "/unapprovedstudents", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Student>> getUnapprovedStudents() {
		return new ResponseEntity<List<Student>>(adminService.listOfUnapprovedStudents(), HttpStatus.OK);
	}

	/**
	 * Adds the admin.
	 *
	 * @param admin the admin
	 * @return the response entity
	 */
	@RequestMapping(value = "/addadmin", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addAdmin(@RequestBody Admin admin) {
		try {
			adminService.addAdmin(admin);
			return new ResponseEntity<String>("Admin Added Successfully", HttpStatus.OK);
		} catch (UserAlreadyExistException e) {
			return new ResponseEntity<String>("Admin Already Exists", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * View admins.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/admins", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Admin>> viewAdmins() {
		return new ResponseEntity<List<Admin>>(adminService.viewAdmins(), HttpStatus.OK);
	}

	/**
	 * Viewprofessors.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/professors", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Professor>> viewprofessors() {
		return new ResponseEntity<List<Professor>>(adminService.viewProfessors(), HttpStatus.OK);
	}

	/**
	 * Adds the professor.
	 *
	 * @param professor the professor
	 * @return the response entity
	 */
	@RequestMapping(value = "/addprofessor", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addProfessor(@RequestBody Professor professor) {
		try {
			adminService.addProfessor(professor);
			return new ResponseEntity<String>("Professor Addedd Successfully", HttpStatus.OK);
		} catch (ProfessorNotAddedException | UserAlreadyExistException e) {
			return new ResponseEntity<String>("Professor Not Added ", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Assign professor to course.
	 *
	 * @param data the data
	 * @return the response entity
	 */
	@RequestMapping(value = "/assignprofessor", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> assignProfessorToCourse(@RequestBody Map<String, String> data) {
		String professorId = data.get("professorId");
		String courseCode = data.get("courseCode");
		try {
			adminService.assignProfessorToCourse(professorId, courseCode);
			return new ResponseEntity<String>("Professor Assigned Successfully", HttpStatus.OK);
		} catch (CourseNotAssignedToProfessorException e) {
			return new ResponseEntity<String>("Professor Not Assigned ", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Adds the course.
	 *
	 * @param course the course
	 * @return the response entity
	 */
	@RequestMapping(value = "/addcourse", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addCourse(@RequestBody Course course) {
		try {
			adminService.addCourse(course);
			return new ResponseEntity<String>("Course Added Successfully", HttpStatus.OK);
		} catch (AddCourseException | ProfessorNotFoundException | CourseFoundException e) {
			return new ResponseEntity<String>("Course Not Added", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Removes the course.
	 *
	 * @param data the data
	 * @return the response entity
	 */
	@RequestMapping(value = "/removecourse", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> removeCourse(@RequestBody Map<String, String> data) {
		String courseCode = data.get("courseCode");
		try {
			adminService.removeCourse(courseCode);
			return new ResponseEntity<String>("Course Removed Successfully", HttpStatus.OK);
		} catch (CourseNotFoundException | CourseNotDeletedException e) {
			return new ResponseEntity<String>("Course Not Removed", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * View course details.
	 *
	 * @param data the data
	 * @return the response entity
	 */
	@RequestMapping(value = "/coursedetails", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Course> viewCourseDetails(@RequestBody Map<String, String> data) {
		String courseCode = data.get("courseCode");
		try {
			return new ResponseEntity<Course>(adminService.viewCourseDetails(courseCode), HttpStatus.OK);
		} catch (CourseNotFoundException e) {
			return new ResponseEntity<Course>(new Course(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Calculatecpi.
	 *
	 * @param data the data
	 * @return the response entity
	 */
	@RequestMapping(value = "/calculatecpi", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Double> calculatecpi(@RequestBody Map<String, String> data) {
		String studentId = data.get("studentId");
		try {
			return new ResponseEntity<Double>(adminService.calculateCpi(studentId), HttpStatus.OK);
		} catch (StudentNotRegisteredException e) {
			return new ResponseEntity<Double>(0.0, HttpStatus.BAD_REQUEST);
		}
	}
}
