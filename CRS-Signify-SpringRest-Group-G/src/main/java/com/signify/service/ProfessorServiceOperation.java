/**
 * 
 */
package com.signify.service;

import com.signify.bean.*;
import com.signify.exception.ProfessorNotTeachingException;
import com.signify.exception.StudentNotRegisteredException;
import com.signify.jdbc.ProfessorDAOImplementation;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dp201
 *
 */

@Service
public class ProfessorServiceOperation extends UserServiceOperation implements ProfessorInterface {

	@Autowired
	private ProfessorDAOImplementation pdi;

	/**
	 * viewing enrolled students
	 */
	public List<Student> viewEnrolledStudents(String professorId) {

		List<Student> es = null;
		try {
			es = pdi.viewEnrolledStudents(professorId);
		} catch (ProfessorNotTeachingException e) {
			System.out.println(e.getMessage());
		}
		return es;
	}

	/**
	 * adding grades
	 */

	public void addGrades(String professorId, String StudentId, String grade)
			throws ProfessorNotTeachingException, StudentNotRegisteredException {
		pdi.addGrades(professorId, StudentId, grade);
	}
}
