package com.signify.restcontroller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.signify.bean.Student;
import com.signify.exception.ProfessorNotTeachingException;
import com.signify.exception.StudentNotRegisteredException;
import com.signify.service.ProfessorServiceOperation;

@RestController
public class ProfessorRestControllerAPI {

	@Autowired
	private ProfessorServiceOperation professorService;

	@RequestMapping(produces = MediaType.APPLICATION_JSON, value = "/enrolledstudents", method = RequestMethod.POST)
	@ResponseBody
	public List<Student> viewEnrolledStudents(@RequestBody Map<String, String> data) {
		return professorService.viewEnrolledStudents(data.get("professorId"));
	}

	@RequestMapping(value = "/addgrades", method = RequestMethod.POST)
	public ResponseEntity<String> addGrades(@RequestBody Map<String, String> data) {
		String professorId = data.get("professorId");
		String studentId = data.get("studentId");
		String grade = data.get("grade");
		try {
			professorService.addGrades(professorId, studentId, grade);
			return new ResponseEntity<String>("Student Graded", HttpStatus.OK);
		} catch (ProfessorNotTeachingException | StudentNotRegisteredException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
	}

}
