package com.signify.jdbc;

import java.util.List;
import com.signify.bean.Student;
import com.signify.exception.ProfessorNotTeachingException;
import com.signify.exception.StudentNotRegisteredException;

public interface ProfessorDAOInterface {
	public List<Student> viewEnrolledStudents(String professorId) throws ProfessorNotTeachingException;
	
	public void addGrades(String professorId , String studentId , String grade) throws StudentNotRegisteredException, ProfessorNotTeachingException;

}