package com.signify.service;
import com.signify.helper.*;
import com.signify.jdbc.StudentDAOImplementation;
import com.signify.bean.*;
import java.util.*;

/**
 * @author dp201
 *
 */
public class StudentServiceOperation extends UserServiceOperation implements StudentInterface {

	StudentDAOImplementation sdi = new StudentDAOImplementation();
	
	public void semesterRegstration() {
		
	}

	public String getStudentId(String userid)
	{
		return sdi.getStudentId(userid);
	}
	
	public int getApprovedStatus(String studentid)
	{
		return sdi.getIsApprovedStatus(studentid);
	}
	
	public void register() {
		
		StudentRegistration sr = new StudentRegistration();
		Student newStudent = sr.registerStudent();
		sdi.register(newStudent);
		System.out.println("\nSTUDENT REGISTERED SUCCESSFULLY! WAITING FOR APPROVAL FROM ADMIN.\n");
	}

	public void viewGrades(String studentid) {
		sdi.viewGrades(studentid);
	}


	public List<RegisteredCourse> viewRegisterCourses(String studentid) {
		return sdi.viewRegisteredCourses(studentid);
	}
	

	public List<Course> getAvailableCourses()
	{
		return sdi.getAvailableCourses();
	}
	
	public void addCourse(String studentid, String courseCode, int type)
	{
		sdi.addCourse(studentid, courseCode, type);
	}
	

	public void dropCourse(String studentid, String courseId) {
		sdi.dropCourse(studentid, courseId);
	}

	public List<Course> getFees(String studentid)
	{
		return sdi.getFees(studentid);
	}
	
	public void payFeesByCash(OfflinePayment ofp, Payment p)
	{
		sdi.payFeesByCash(ofp, p);
		return;
	}
	public void payFeesByCheque(OfflinePayment ofp, Payment p)
	{
		sdi.payFeesByCheque(ofp, p);
		return;
	}
	public void payFeesByCard(OnlinePayment onp, Payment p)
	{
		sdi.payFeesByCard(onp, p);
		return;
	}
	public void payFeesByNetBanking(OnlinePayment onp, Payment p)
	{
		sdi.payFeesByNetBanking(onp, p);
		return;
	}

}
