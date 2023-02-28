package com.signify.restcontroller;

import java.util.ArrayList;
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

import com.signify.bean.Course;
import com.signify.bean.Grades;
import com.signify.bean.OfflinePayment;
import com.signify.bean.OnlinePayment;
import com.signify.bean.RegisteredCourse;
import com.signify.bean.Student;
import com.signify.bean.User;
import com.signify.exception.CourseLimitExceedException;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.SemesterNotRegisteredException;
import com.signify.exception.StudentNotRegisteredException;
import com.signify.service.StudentServiceOperation;

@RestController
public class StudentRestControllerAPI {

	@Autowired
	private StudentServiceOperation studentService;

	@RequestMapping(value = "/getstudentid", method = RequestMethod.POST)
	@ResponseBody
	public String getStudentId(@RequestBody User st) {
		return studentService.getStudentId(st.getUserId());
	}

	@RequestMapping(value = "/getapprovedstatus", method = RequestMethod.POST)
	public int getApprovedStatus(@RequestBody Student studentId) {
		return studentService.getApprovedStatus(studentId.getStudentId());
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> registerStudent(@RequestBody Student student) {
		System.out.println(student.getUsername());
		studentService.register(student);
		return new ResponseEntity<String>("Student Registered Successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/registersemester", method = RequestMethod.POST)
	public ResponseEntity<String> semesterRegistration(@RequestBody Map<String, String> data) {
		int semester = Integer.valueOf(data.get("semester"));
		String studentId = data.get("studentId");
		try {
			studentService.semesterRegister(studentId, semester);
			return new ResponseEntity<String>("Student Registered For Semester " + semester, HttpStatus.OK);
		} catch (StudentNotRegisteredException e) {
			return new ResponseEntity<String>("Wrong Student Id or Semester ", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/availablecourses", method = RequestMethod.POST)
	public ResponseEntity<List<Course>> getAvailableCourses(@RequestBody Map<String, String> data) {
		try {
			return new ResponseEntity<List<Course>>(studentService.getAvailableCourses(data.get("studentId")),
					HttpStatus.OK);
		} catch (SemesterNotRegisteredException e) {
			return new ResponseEntity<List<Course>>(new ArrayList<Course>(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/registercourse", method = RequestMethod.POST)
	public ResponseEntity<String> addCourse(@RequestBody Map<String, String> data) {
		String studentId = data.get("studentId");
		String courseCode = data.get("courseCode");
		int type = Integer.valueOf(data.get("type"));

		try {
			studentService.addCourse(studentId, courseCode, type);
			return new ResponseEntity<String>("Course Added Successfully", HttpStatus.OK);
		} catch (CourseLimitExceedException | CourseNotFoundException e) {
			return new ResponseEntity<String>("Course Could Not Be Added", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/dropcourse", method = RequestMethod.POST)
	public ResponseEntity<String> dropCourse(@RequestBody Map<String, String> data) {
		String studentId = data.get("studentId");
		String courseCode = data.get("courseCode");

		try {
			studentService.dropCourse(studentId, courseCode);
			return new ResponseEntity<String>("Course Added Successfully", HttpStatus.OK);
		} catch (CourseNotFoundException e) {
			return new ResponseEntity<String>("Course Could Not Be Added", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/grades", method = RequestMethod.POST)
	public ResponseEntity<List<Grades>> viewGrades(@RequestBody Map<String, String> data) {
		String studentId = data.get("studentId");

		return new ResponseEntity<List<Grades>>(studentService.viewGrades(studentId), HttpStatus.OK);
	}

	@RequestMapping(value = "/registeredcourse", method = RequestMethod.POST)
	public ResponseEntity<List<RegisteredCourse>> viewRegisteredCourse(@RequestBody Map<String, String> data) {
		String studentId = data.get("studentId");

		return new ResponseEntity<List<RegisteredCourse>>(studentService.viewRegisterCourses(studentId), HttpStatus.OK);
	}

	@RequestMapping(value = "/fees", method = RequestMethod.POST)
	public ResponseEntity<List<Course>> getFees(@RequestBody Map<String, String> data) {
		String studentId = data.get("studentId");

		return new ResponseEntity<List<Course>>(studentService.getFees(studentId), HttpStatus.OK);
	}

	@RequestMapping(value = "/payfeesbycash", method = RequestMethod.POST)
	public ResponseEntity<String> payFeesByCash(@RequestBody OfflinePayment ofp) {
		studentService.payFeesByCash(ofp);
		return new ResponseEntity<String>("Fee Paid by cash Successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/payfeesbycheque", method = RequestMethod.POST)
	public ResponseEntity<String> payFeesByCheque(@RequestBody OfflinePayment ofp) {
		studentService.payFeesByCheque(ofp);
		return new ResponseEntity<String>("Fee paid by cheque Successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/payfeesbycard", method = RequestMethod.POST)
	public ResponseEntity<String> payFeesByCard(@RequestBody OnlinePayment onp) {
		studentService.payFeesByCard(onp);
		return new ResponseEntity<String>("Fee paid by card Successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/payfeesbynetbanking", method = RequestMethod.POST)
	public ResponseEntity<String> payFeesByNetbanking(@RequestBody OnlinePayment onp) {
		studentService.payFeesByNetBanking(onp);
		return new ResponseEntity<String>("Fee paid by net banking successfully", HttpStatus.OK);
	}
}
