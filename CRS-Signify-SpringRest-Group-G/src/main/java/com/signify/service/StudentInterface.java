/**
 * 
 */
package com.signify.service;

import java.util.List;

import com.signify.bean.Course;
import com.signify.bean.Grades;
import com.signify.bean.OfflinePayment;
import com.signify.bean.OnlinePayment;
import com.signify.bean.Payment;
import com.signify.bean.RegisteredCourse;
import com.signify.bean.Student;
import com.signify.exception.CourseLimitExceedException;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.SemesterNotRegisteredException;
import com.signify.exception.StudentNotRegisteredException;

/**
 * @author dp201
 *
 */
public interface StudentInterface {
	/**
	 * Method for student registeration
	 */
	public void register(Student student);

	/**
	 * Method to get studnet id
	 * 
	 * @param userid
	 * @return
	 */
	public String getStudentId(String userid);

	/**
	 * Method to get approved status
	 * 
	 * @param studentId
	 * @return
	 */
	public int getApprovedStatus(String studentId);

	/**
	 * Method to get available courses
	 * 
	 * @param studentid
	 * @return
	 * @throws SemesterNotRegisteredException
	 */
	public List<Course> getAvailableCourses(String studentid) throws SemesterNotRegisteredException;

	/**
	 * Method for semester registration
	 * 
	 * @param studentid
	 * @param semester
	 * @throws StudentNotRegisteredException
	 */
	public void semesterRegister(String studentid, int semester) throws StudentNotRegisteredException;

	/**
	 * Method to add course
	 * 
	 * @param studentId
	 * @param courseCode
	 * @param type
	 * @throws CourseNotFoundException
	 * @throws CourseLimitExceedException
	 */
	public void addCourse(String studentId, String courseCode, int type)
			throws CourseLimitExceedException, CourseNotFoundException;

	/**
	 * Method to drop course
	 * 
	 * @param studentId
	 * @param courseId
	 * @throws CourseNotFoundException
	 */
	public void dropCourse(String studentId, String courseId) throws CourseNotFoundException;

	/**
	 * Method to view registered courses
	 * 
	 * @param studentId
	 * @return
	 */
	public List<RegisteredCourse> viewRegisterCourses(String studentId);

	/**
	 * Method to view grades
	 * 
	 * @param studentId
	 * @return List of grades
	 */
	public List<Grades> viewGrades(String studentId);

	public List<Course> getFees(String studentId);

	/**
	 * Method to pay fees by card
	 * 
	 * @param onp
	 * @param p
	 */
	public void payFeesByCard(OnlinePayment onp);

	/**
	 * Method to pay fees by net banking
	 * 
	 * @param onp
	 * @param p
	 */
	public void payFeesByNetBanking(OnlinePayment onp);

	/**
	 * Method to pay fees by cash
	 * 
	 * @param ofp
	 * @param p
	 */
	public void payFeesByCash(OfflinePayment ofp);

	/**
	 * Method to pay fees by cheque
	 * 
	 * @param ofp
	 * @param p
	 */
	public void payFeesByCheque(OfflinePayment ofp);

}
