package com.signify.jdbc;
import com.signify.bean.*;
import java.util.*;

public interface StudentDAOInterface {
	public void register(String username, String password, String address, int sem, String branch, String batch, String bg, String fname, String phnum, String doj);
	
	public boolean getIsApprovedStatus(int student_id);
	
	public List<Course> getAvailableCourses();
	
	public void addCourse(int studentId, String courseCode, int type);
	
	public void dropCourse(int studentId, String courseCode);
	
	public List<Grades> viewGrades(int studentId);	
	
	public String[] viewGrade(int studentId, String courseCode);
		
	public List<Course> viewRegisteredCourses(int studentId);
		
	public void payFees(int studentId);
}
