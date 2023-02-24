package com.signify.jdbc;
import com.signify.bean.*;
import com.signify.exception.CourseLimitExceedException;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.SemesterNotRegisteredException;
import com.signify.exception.StudentNotRegisteredException;

import java.util.*;

public interface StudentDAOInterface {
	public void register(Student student);
	
	public void semesterRegister(String studentid, int sem) throws StudentNotRegisteredException;
	
	public String getStudentId(String userid);
	
	public int getIsApprovedStatus(String studentid);
	
	public List<Course> getAvailableCourses(String studentId) throws SemesterNotRegisteredException;
	
	public void addCourse(String studentid, String courseCode, int type) throws CourseNotFoundException, CourseLimitExceedException;
	
	public void dropCourse(String studentid, String courseCode) throws CourseNotFoundException;
	
	public List<Grades> viewGrades(String studentid);	
		
	public List<RegisteredCourse> viewRegisteredCourses(String studentid);
		
	public List<Course> getFees(String studentid);
	
	public void payFeesByCheque(OfflinePayment ofp, Payment p);
	public void payFeesByCash(OfflinePayment ofp, Payment p);
	public void payFeesByCard(OnlinePayment onp, Payment p);
	public void payFeesByNetBanking(OnlinePayment onp, Payment p);
}
