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

/**
 * @author dp201
 *
 */
public interface StudentInterface {
	/**
	 *Method for  student registeration
	 */
	public void register();
	/**
	 * Method to get studnet id
	 * @param userid
	 * @return
	 */
	public String getStudentId(String userid);
	/**
	 * Method to get approved status
	 * @param studentId
	 * @return
	 */
	public int getApprovedStatus(String studentId);
	/**
	 * Method to get available courses
	 * @param studentid
	 * @return
	 */
	public List<Course> getAvailableCourses(String studentid);
	/**
	 * Method for semester registration
	 * @param studentid
	 * @param semester
	 */
	public void semesterRegister(String studentid, int semester);
	/**
	 * Method to add course
	 * @param studentId
	 * @param courseCode
	 * @param type
	 */
	public void addCourse(String studentId, String courseCode, int type);
	/**
	 * Method to drop course
	 * @param studentId
	 * @param courseId
	 */
	public void dropCourse(String studentId, String courseId);	
	/**
	 * Method to view registered courses
	 * @param studentId
	 * @return
	 */
	public List<RegisteredCourse> viewRegisterCourses(String studentId);
	/**
	 * Method to view grades
	 * @param studentId
	 */
	public void viewGrades(String studentId);
	/**
	 * Method to get fees
	 * @param studentId
	 * @return
	 */
	public List<Course> getFees(String studentId);
	/**
	 * Method to pay fees by card
	 * @param onp
	 * @param p
	 */
	public void payFeesByCard(OnlinePayment onp, Payment p);
	/**
	 * Method to pay fees by net banking
	 * @param onp
	 * @param p
	 */
	public void payFeesByNetBanking(OnlinePayment onp, Payment p);
	/**
	 * Method to pay fees by cash
	 * @param ofp
	 * @param p
	 */
	public void payFeesByCash(OfflinePayment ofp, Payment p);
	/**
	 * Method to pay fees by cheque
	 * @param ofp
	 * @param p
	 */
	public void payFeesByCheque(OfflinePayment ofp, Payment p);

}
