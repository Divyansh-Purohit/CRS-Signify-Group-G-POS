package com.signify.service;
import com.signify.helper.*;
import com.signify.jdbc.StudentDAOImplementation;
import com.signify.bean.*;
import com.signify.exception.CourseLimitExceedException;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.SemesterNotRegisteredException;
import com.signify.exception.StudentNotRegisteredException;

import java.util.*;

/**
 * @author dp201
 *
 */
public class StudentServiceOperation extends UserServiceOperation implements StudentInterface {

	StudentDAOImplementation sdi = new StudentDAOImplementation();
	
	public void semesterRegister(String studentid, int sem) {
			sdi.semesterRegister(studentid, sem);
			System.out.println("\nSEMESTER SET TO "+sem+" FOR STUDENT ID \""+studentid+"\"\n");
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
	

	public List<Course> getAvailableCourses(String studentid)
	{
		List<Course> c= null;
		try {
			 c = sdi.getAvailableCourses(studentid);
		}catch(SemesterNotRegisteredException e)
		{
			System.out.println(e.getMessage());
		}
		return c;
	}
	
	public void addCourse(String studentid, String courseCode, int type)
	{
		try {
			sdi.addCourse(studentid, courseCode, type);
			String courseType = type == 1 ? "PRIMARY" : "ALTERNATE";
			System.out.println("\nSUCCESSFULLY REGISTERED FOR COURSE WITH COURSE CODE \""+courseCode+"\" (TYPE: "+courseType+")\n");
		}catch(CourseLimitExceedException e)
		{
			System.out.println(e.getMessage());
		}
	}
	

	public void dropCourse(String studentid, String courseId) {
		try {
			sdi.dropCourse(studentid, courseId);
		} catch (CourseNotFoundException e) {
			System.out.println("\nYOU ARE NOT REGISTERED FOR COURSE WITH COURSE CODE \""+courseId+"\"!\n");
		}
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
