package com.signify.service;
import java.util.List;
import com.signify.bean.Student;
import com.signify.exception.ProfessorNotTeachingException;

public interface ProfessorInterface {
	public List<Student> viewEnrolledStudents(String professorId);
	public void addGrades(String professorId, String studentId, String grade);

}
