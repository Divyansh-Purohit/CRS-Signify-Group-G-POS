package com.signify.jdbc;

import java.util.List;
import com.signify.bean.Student;

public interface ProfessorDAOInterface {
	public List<Integer> viewEnrolledStudents(int professorId);
	
	public void addGrades(int professorId , int studentId , String grade);

}