package com.signify.jdbc;

public interface ProfessorDAOInterface {
	public void viewEnrolledStudents(int professorId);
	
	public void addGrades(int professorId, int studentId);

}
