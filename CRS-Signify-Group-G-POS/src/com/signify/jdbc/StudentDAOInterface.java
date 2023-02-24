package com.signify.jdbc;
import com.signify.bean.*;
import com.signify.exception.CourseLimitExceedException;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.SemesterNotRegisteredException;
import com.signify.exception.StudentNotRegisteredException;

import java.util.*;

public interface StudentDAOInterface {
	/**
	 * Method to register a student
	 * 
	 * @param student
	 */
	public void register(Student student);
	/**
	 * Method to do semester registration
	 * 
	 * @param studentid
	 * @param sem
	 * @throws StudentNotRegisteredException
	 */
			
	
	public void semesterRegister(String studentid, int sem) throws StudentNotRegisteredException;
	
	/**
	 * Method to get student id
	 * 
	 * @param userid
	 * @return studentid
	 */
	public String getStudentId(String userid);
	/**
	 * Method to get approval status of a student
	 * 
	 * @param studentid
	 * @return 1 or 0 representing the approval status
	 */
	public int getIsApprovedStatus(String studentid);
	/**
	 * Method to view list of available courses
	 * @param studentId
	 * @return list of available courses
	 * @throws SemesterNotRegisteredException
	 */
	public List<Course> getAvailableCourses(String studentId) throws SemesterNotRegisteredException;
	
	/**
	 * Method to add a new course
	 * 
	 * @param studentid
	 * @param courseCode
	 * @param type
	 * @throws CourseNotFoundException
	 * @throws CourseLimitExceedException
	 */
	public void addCourse(String studentid, String courseCode, int type) throws CourseNotFoundException, CourseLimitExceedException;
	
	/**
	 * Method to drop a course
	 * 
	 * @param studentid
	 * @param courseCode
	 * @throws CourseNotFoundException
	 */
	public void dropCourse(String studentid, String courseCode) throws CourseNotFoundException;
	/**
	 * Method to view grades
	 * 
	 * @param studentid
	 * @return
	 */
	public List<Grades> viewGrades(String studentid);	
	/**
	 * Method to view registered courses
	 * 
	 * @param studentid
	 * @return list of registered courses
	 */
	public List<RegisteredCourse> viewRegisteredCourses(String studentid);
		
	/**
	 * Method to get fees for a particular student
	 * 
	 * @param studentid
	 * @return list of courses along with their fees
	 */
	public List<Course> getFees(String studentid);
	/**
	 * Method to pay fees by cheque
	 * 
	 * @param ofp
	 * @param p
	 */
	
	public void payFeesByCheque(OfflinePayment ofp, Payment p);
	/*
	 * Method to pay fees by cash
	 */
	public void payFeesByCash(OfflinePayment ofp, Payment p);
	/**
	 * Method to pay fees by card
	 * 
	 * @param onp
	 * @param p
	 */
	public void payFeesByCard(OnlinePayment onp, Payment p);
	/**
	 * Method to pay fees by netbanking
	 * 
	 * @param onp
	 * @param p
	 */
	public void payFeesByNetBanking(OnlinePayment onp, Payment p);
}
