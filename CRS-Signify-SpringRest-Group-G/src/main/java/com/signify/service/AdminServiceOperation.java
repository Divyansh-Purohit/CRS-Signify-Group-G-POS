package com.signify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.exception.AddCourseException;
import com.signify.exception.CourseFoundException;
import com.signify.exception.CourseNotAssignedToProfessorException;
import com.signify.exception.CourseNotDeletedException;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.ProfessorNotAddedException;
import com.signify.exception.ProfessorNotFoundException;
import com.signify.exception.StudentNotFoundForVerificationException;
import com.signify.exception.StudentNotRegisteredException;
import com.signify.exception.UserAlreadyExistException;
import com.signify.jdbc.AdminDAOImplementation;

/**
 * @author dp201
 *
 */
/**
 *
 * Implementations of Admin Operations
 *
 */

@Service
public class AdminServiceOperation extends UserServiceOperation implements AdminInterface {

	@Autowired
	private AdminDAOImplementation adi;

	/**
	 * returns list of unapproved students
	 */

	@Override
	public List<Student> listOfUnapprovedStudents() {

		return adi.listOfUnapprovedStudents();
	}

	/**
	 * approves all students
	 */
	@Override
	public void approveAllStudents() {

		adi.approveAllStudents();
		return;
	}

	/**
	 * approving students by id
	 * 
	 * @throws StudentNotFoundForVerificationException
	 */
	@Override
	public void approveStudentById(String studentid) throws StudentNotFoundForVerificationException {
		adi.approveStudentById(studentid);
	}

	/**
	 * adding professor to db
	 * 
	 * @throws UserAlreadyExistException
	 * @throws ProfessorNotAddedException
	 */
	@Override
	public void addProfessor(Professor newProfessor) throws ProfessorNotAddedException, UserAlreadyExistException {
		adi.addProfessor(newProfessor);
	}

	/**
	 * return list of professors
	 */
	@Override
	public List<Professor> viewProfessors() {

		return adi.viewProfessors();
	}

	@Override
	public List<Admin> viewAdmins() {
		return adi.viewAdmins();
	}

	/**
	 * assigning professor to course
	 * 
	 * @throws CourseNotAssignedToProfessorException
	 */
	@Override
	public void assignProfessorToCourse(String professorId, String courseCode)
			throws CourseNotAssignedToProfessorException {
		adi.assignProfessorToCourse(professorId, courseCode);
	}

	/**
	 * adding admin to db
	 * 
	 * @throws UserAlreadyExistException
	 */
	@Override
	public void addAdmin(Admin newAdmin) throws UserAlreadyExistException {
		adi.addAdmin(newAdmin);
		return;
	}

	/**
	 * adding course to db
	 * 
	 * @throws CourseFoundException
	 * @throws ProfessorNotFoundException
	 * @throws AddCourseException
	 */
	@Override
	public void addCourse(Course c) throws AddCourseException, ProfessorNotFoundException, CourseFoundException {
		adi.addCourse(c);
	}

	/**
	 * removing course from db
	 * 
	 * @throws CourseNotDeletedException
	 * @throws CourseNotFoundException
	 */
	@Override
	public void removeCourse(String courseCode) throws CourseNotFoundException, CourseNotDeletedException {
		adi.removeCourse(courseCode);
	}

	/**
	 * viewing course details
	 * 
	 * @throws CourseNotFoundException
	 */
	@Override
	public Course viewCourseDetails(String courseCode) throws CourseNotFoundException {
		return adi.viewCourseDetails(courseCode);
	}

	/**
	 * calculating cpi
	 * 
	 * @throws StudentNotRegisteredException
	 */
	@Override
	public double calculateCpi(String studentid) throws StudentNotRegisteredException {
		return adi.calculateCpi(studentid);
	}

	@Override
	public void generateReportCard(String studentId) {

	}
}