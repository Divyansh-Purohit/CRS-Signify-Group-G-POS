package com.signify.jdbc;
import com.signify.bean.*;
import java.util.*;

public interface StudentDAOInterface {
	public void register(Student student);
	
	public String getStudentId(String userid);
	
	public int getIsApprovedStatus(String studentid);
	
	public List<Course> getAvailableCourses();
	
	public void addCourse(String studentid, String courseCode, int type);
	
	public void dropCourse(String studentid, String courseCode);
	
	public List<Grades> viewGrades(String studentid);	
		
	public List<RegisteredCourse> viewRegisteredCourses(String studentid);
		
	public List<Course> getFees(String studentid);
	
	public void payFeesByCheque(OfflinePayment ofp, Payment p);
	public void payFeesByCash(OfflinePayment ofp, Payment p);
	public void payFeesByCard(OnlinePayment onp, Payment p);
	public void payFeesByNetBanking(OnlinePayment onp, Payment p);
}
