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
	/**
	 * semester registeration 
	 */
	public void semesterRegister(String studentid, int sem) {
			sdi.semesterRegister(studentid, sem);
			System.out.println("\nSEMESTER SET TO "+sem+" FOR STUDENT ID \""+studentid+"\"\n");
	}
	
	/**
	 * return student id
	 */
	public String getStudentId(String userid)
	{
		return sdi.getStudentId(userid);
	}
	/**
	 * return approved status
	 */
	public int getApprovedStatus(String studentid)
	{
		return sdi.getIsApprovedStatus(studentid);
	}
	
	/**
	 * student registeration
	 */
	public void register() {
		
		StudentRegistration sr = new StudentRegistration();
		Student newStudent = sr.registerStudent();
		sdi.register(newStudent);
		System.out.println("\nSTUDENT REGISTERED SUCCESSFULLY! WAITING FOR APPROVAL FROM ADMIN.\n");
	}

	/**
	 * viewing grades
	 */
	public List<Grades> viewGrades(String studentid) {
		return sdi.viewGrades(studentid);
	}
	/**
	 * return list of grades of registered courses
	 */

	public List<RegisteredCourse> viewRegisterCourses(String studentid) {
		return sdi.viewRegisteredCourses(studentid);
	}
	
	/**
	 * return list of available courses
	 */
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
	/**
	 * adding course
	 */
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
	
	/**
	 * drop course
	 */
	public void dropCourse(String studentid, String courseId) {
		try {
			sdi.dropCourse(studentid, courseId);
		} catch (CourseNotFoundException e) {
			System.out.println("\nYOU ARE NOT REGISTERED FOR COURSE WITH COURSE CODE \""+courseId+"\"!\n");
		}
	}
	/**
	 * return amount of fees
	 */
	public List<Course> getFees(String studentid)
	{
		return sdi.getFees(studentid);
	}
	/**
	 * paying fees by cash
	 */
	public void payFeesByCash(OfflinePayment ofp, Payment p)
	{
		sdi.payFeesByCash(ofp, p);
		return;
	}
	
	/**
	 * paying fees by cheque
	 */
	public void payFeesByCheque(OfflinePayment ofp, Payment p)
	{
		sdi.payFeesByCheque(ofp, p);
		return;
	}
	/**
	 * paying fees by card
	 */
	public void payFeesByCard(OnlinePayment onp, Payment p)
	{
		sdi.payFeesByCard(onp, p);
		return;
	}
	/**
	 * paying fees by net banking
	 */
	public void payFeesByNetBanking(OnlinePayment onp, Payment p)
	{
		sdi.payFeesByNetBanking(onp, p);
		return;
	}
}
