package com.signify.jdbc;

import java.util.List;
import com.signify.bean.Student;

public interface ProfessorDAOInterface {
	public List<Student> viewEnrolledStudents(String professorId);
	
	public void addGrades(String professorId , String studentId , String grade);

}