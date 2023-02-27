package com.signify.service;

import java.util.List;
import com.signify.bean.Student;
import com.signify.exception.ProfessorNotTeachingException;
import com.signify.exception.StudentNotRegisteredException;

public interface ProfessorInterface {
	/**
	 * Method to view enrolled students
	 * 
	 * @param professorId
	 * @return
	 */
	public List<Student> viewEnrolledStudents(String professorId);

	/**
	 * Method to add grades
	 * 
	 * @param professorId
	 * @param studentId
	 * @param grade
	 */
	public void addGrades(String professorId, String studentId, String grade)
			throws ProfessorNotTeachingException, StudentNotRegisteredException;

}
