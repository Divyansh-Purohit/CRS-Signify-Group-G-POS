package com.signify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.signify.helper.StudentRegistration;
import com.signify.jdbc.StudentDAOImplementation;

/**
 * @author dp201
 *
 */

@Service
public class StudentServiceOperation extends UserServiceOperation implements StudentInterface {

	@Autowired
	StudentDAOImplementation sdi;

	/**
	 * semester registeration
	 * 
	 * @throws StudentNotRegisteredException
	 */
	@Override
	public void semesterRegister(String studentid, int sem) throws StudentNotRegisteredException {
		sdi.semesterRegister(studentid, sem);
	}

	/**
	 * return student id
	 */
	@Override
	public String getStudentId(String userid) {
		return sdi.getStudentId(userid);
	}

	/**
	 * return approved status
	 */
	@Override
	public int getApprovedStatus(String studentid) {
		return sdi.getIsApprovedStatus(studentid);
	}

	/**
	 * student registeration
	 */
	@Override
	public void register(Student student) {
		sdi.register(student);
	}

	/**
	 * viewing grades
	 */
	@Override
	public List<Grades> viewGrades(String studentid) {
		return sdi.viewGrades(studentid);
	}

	/**
	 * return list of grades of registered courses
	 */
	@Override
	public List<RegisteredCourse> viewRegisterCourses(String studentid) {
		return sdi.viewRegisteredCourses(studentid);
	}

	/**
	 * return list of available courses
	 * 
	 * @throws SemesterNotRegisteredException
	 */
	@Override
	public List<Course> getAvailableCourses(String studentid) throws SemesterNotRegisteredException {
		return sdi.getAvailableCourses(studentid);
	}

	/**
	 * adding course
	 * 
	 * @throws CourseNotFoundException
	 * @throws CourseLimitExceedException
	 */
	@Override
	public void addCourse(String studentid, String courseCode, int type)
			throws CourseLimitExceedException, CourseNotFoundException {
		sdi.addCourse(studentid, courseCode, type);
	}

	/**
	 * drop course
	 * 
	 * @throws CourseNotFoundException
	 */
	@Override
	public void dropCourse(String studentid, String courseId) throws CourseNotFoundException {
		sdi.dropCourse(studentid, courseId);
	}

	/**
	 * return amount of fees
	 */
	@Override
	public List<Course> getFees(String studentid) {
		return sdi.getFees(studentid);
	}

	/**
	 * paying fees by cash
	 */
	@Override
	public void payFeesByCash(OfflinePayment ofp) {
		sdi.payFeesByCash(ofp);
		return;
	}

	/**
	 * paying fees by cheque
	 */
	@Override
	public void payFeesByCheque(OfflinePayment ofp) {
		sdi.payFeesByCheque(ofp);
		return;
	}

	/**
	 * paying fees by card
	 */
	@Override
	public void payFeesByCard(OnlinePayment onp) {
		sdi.payFeesByCard(onp);
		return;
	}

	/**
	 * paying fees by net banking
	 */
	@Override
	public void payFeesByNetBanking(OnlinePayment onp) {
		sdi.payFeesByNetBanking(onp);
		return;
	}
}
