package com.signify.service;
import com.signify.collection.UserData;
import com.signify.helper.*;
import com.signify.jdbc.StudentDAOImplementation;
import com.signify.bean.*;
import helper.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


	public void viewRegisterCourses(String studentid) {
		List<RegisteredCourse> rcourses = sdi.viewRegisteredCourses(studentid);
		System.out.println("Course_Code\tCourse_Name\tSemester\tType");
		System.out.println("=================================================");
		for (RegisteredCourse x : rcourses) {
			System.out.println(x.getCourseCode() + "\t" + x.getCourseName() + "\t" + x.getSemester() + "\t" + x.getType());
		}
		System.out.println();
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

	public double getFees(String studentid)
	{
		return 0.0;
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
