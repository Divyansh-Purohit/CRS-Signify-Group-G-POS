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

/**
 * @author HIMANSHU YADAV
 * The Class StudentRestControllerAPI.
 */
@RestController
public class StudentRestControllerAPI {

	/** The student service. */
	@Autowired
	private StudentServiceOperation studentService;

	/**
	 * Gets the student id.
	 *
	 * @param st the st
	 * @return the student id
	 */
	@RequestMapping(value = "/getstudentid", method = RequestMethod.POST)
	@ResponseBody
	public String getStudentId(@RequestBody User st) {
		return studentService.getStudentId(st.getUserId());
	}

	/**
	 * Gets the approved status.
	 *
	 * @param studentId the student id
	 * @return the approved status
	 */
	@RequestMapping(value = "/getapprovedstatus", method = RequestMethod.POST)
	public int getApprovedStatus(@RequestBody Student studentId) {
		return studentService.getApprovedStatus(studentId.getStudentId());
	}

	/**
	 * Register student.
	 *
	 * @param student the student
	 * @return the response entity
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> registerStudent(@RequestBody Student student) {
		System.out.println(student.getUsername());
		studentService.register(student);
		return new ResponseEntity<String>("Student Registered Successfully", HttpStatus.OK);
	}

	/**
	 * Semester registration.
	 *
	 * @param data the data
	 * @return the response entity
	 */
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

	/**
	 * Gets the available courses.
	 *
	 * @param data the data
	 * @return the available courses
	 */
	@RequestMapping(value = "/availablecourses", method = RequestMethod.POST)
	public ResponseEntity<List<Course>> getAvailableCourses(@RequestBody Map<String, String> data) {
		try {
			return new ResponseEntity<List<Course>>(studentService.getAvailableCourses(data.get("studentId")),
					HttpStatus.OK);
		} catch (SemesterNotRegisteredException e) {
			return new ResponseEntity<List<Course>>(new ArrayList<Course>(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Adds the course.
	 *
	 * @param data the data
	 * @return the response entity
	 */
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

	/**
	 * Drop course.
	 *
	 * @param data the data
	 * @return the response entity
	 */
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

	/**
	 * View grades.
	 *
	 * @param data the data
	 * @return the response entity
	 */
	@RequestMapping(value = "/grades", method = RequestMethod.POST)
	public ResponseEntity<List<Grades>> viewGrades(@RequestBody Map<String, String> data) {
		String studentId = data.get("studentId");

		return new ResponseEntity<List<Grades>>(studentService.viewGrades(studentId), HttpStatus.OK);
	}

	/**
	 * View registered course.
	 *
	 * @param data the data
	 * @return the response entity
	 */
	@RequestMapping(value = "/registeredcourse", method = RequestMethod.POST)
	public ResponseEntity<List<RegisteredCourse>> viewRegisteredCourse(@RequestBody Map<String, String> data) {
		String studentId = data.get("studentId");

		return new ResponseEntity<List<RegisteredCourse>>(studentService.viewRegisterCourses(studentId), HttpStatus.OK);
	}

	/**
	 * Gets the fees.
	 *
	 * @param data the data
	 * @return the fees
	 */
	@RequestMapping(value = "/fees", method = RequestMethod.POST)
	public ResponseEntity<List<Course>> getFees(@RequestBody Map<String, String> data) {
		String studentId = data.get("studentId");

		return new ResponseEntity<List<Course>>(studentService.getFees(studentId), HttpStatus.OK);
	}

	/**
	 * Pay fees by cash.
	 *
	 * @param ofp the ofp
	 * @return the response entity
	 */
	@RequestMapping(value = "/payfeesbycash", method = RequestMethod.POST)
	public ResponseEntity<String> payFeesByCash(@RequestBody OfflinePayment ofp) {
		studentService.payFeesByCash(ofp);
		return new ResponseEntity<String>("Fee Paid by cash Successfully", HttpStatus.OK);
	}

	/**
	 * Pay fees by cheque.
	 *
	 * @param ofp the ofp
	 * @return the response entity
	 */
	@RequestMapping(value = "/payfeesbycheque", method = RequestMethod.POST)
	public ResponseEntity<String> payFeesByCheque(@RequestBody OfflinePayment ofp) {
		studentService.payFeesByCheque(ofp);
		return new ResponseEntity<String>("Fee paid by cheque Successfully", HttpStatus.OK);
	}

	/**
	 * Pay fees by card.
	 *
	 * @param onp the onp
	 * @return the response entity
	 */
	@RequestMapping(value = "/payfeesbycard", method = RequestMethod.POST)
	public ResponseEntity<String> payFeesByCard(@RequestBody OnlinePayment onp) {
		studentService.payFeesByCard(onp);
		return new ResponseEntity<String>("Fee paid by card Successfully", HttpStatus.OK);
	}

	/**
	 * Pay fees by netbanking.
	 *
	 * @param onp the onp
	 * @return the response entity
	 */
	@RequestMapping(value = "/payfeesbynetbanking", method = RequestMethod.POST)
	public ResponseEntity<String> payFeesByNetbanking(@RequestBody OnlinePayment onp) {
		studentService.payFeesByNetBanking(onp);
		return new ResponseEntity<String>("Fee paid by net banking successfully", HttpStatus.OK);
	}
}
