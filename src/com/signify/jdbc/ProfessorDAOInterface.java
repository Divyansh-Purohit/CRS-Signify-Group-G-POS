package com.signify.jdbc;

import java.util.List;
import com.signify.bean.Student;
import com.signify.exception.ProfessorNotTeachingException;
import com.signify.exception.StudentNotRegisteredException;

public interface ProfessorDAOInterface {
	/**
	 * Method to view list of enrolled students
	 * 
	 * @param professorId
	 * @return the list of enrolled students
	 * @throws ProfessorNotTeachingException
	 */
	public List<Student> viewEnrolledStudents(String professorId) throws ProfessorNotTeachingException;
	/**
	 * Method to add grades
	 * 
	 * @param professorId
	 * @param studentId
	 * @param grade
	 * @throws StudentNotRegisteredException
	 * @throws ProfessorNotTeachingException
	 */
	public void addGrades(String professorId , String studentId , String grade) throws StudentNotRegisteredException, ProfessorNotTeachingException;

}