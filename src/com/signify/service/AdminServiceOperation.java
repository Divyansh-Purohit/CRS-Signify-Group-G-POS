package com.signify.service;

import com.signify.bean.*;
import com.signify.collection.UserData;
import com.signify.jdbc.AdminDAOImplementation;
import com.signify.exception.*;

import helper.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author dp201
 *
 */
public class AdminServiceOperation extends UserServiceOperation implements AdminInterface {

	public AdminDAOImplementation adi = new AdminDAOImplementation();

	public List<Student> listOfUnapprovedStudents() {
		return adi.listOfUnapprovedStudents();
	}

	public void approveAllStudents() {
		adi.approveAllStudents();
		return;
	}

	public void approveStudentById(String studentid) {
		try {
			adi.approveStudentById(studentid);
		} catch (StudentNotFoundForVerificationException e) {
			System.out.println(e.getMessage());
		}
		return;
	}

	public void addProfessor(Professor newProfessor) {
		try {
			adi.addProfessor(newProfessor);
			System.out.println("\nNEW PROFESSOR ADDED TO DB SUCCESSFULLY!\n");
		} catch (ProfessorNotAddedException | UserAlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		return;
	}

	public List<Professor> viewProfessors() {
		return adi.viewProfessors();
	}

	public List<Admin> viewAdmins() {
		return adi.viewAdmins();
	}

	public void assignProfessorToCourse(String professorId, String courseCode) {
		try {
			adi.assignProfessorToCourse(professorId, courseCode);
			System.out.println("\nProfessor Assigned To Course Successfully!\n");
		} catch (CourseNotAssignedToProfessorException e) {
			System.out.println(e.getMessage());
		}
	}

	public void addAdmin(Admin newAdmin) {
		try {
			adi.addAdmin(newAdmin);
			System.out.println("\nNEW ADMIN ADDED TO DB SUCCESSFULLY!\n");

		} catch (UserAlreadyExistException e) {
			System.out.println(e.getMessage());
		}

		return;
	}

	public void addCourse(Course c) {
		try {
			adi.addCourse(c);
			System.out.println("\nCOURSE ADDED TO CATALOG SUCCESSFULLY!\n");
		} catch (CourseFoundException | ProfessorNotFoundException | AddCourseException e) {
			System.out.println(e.getMessage());
		}
		return;
	}

	public void removeCourse(String courseCode) {
		try {
			adi.removeCourse(courseCode);
			System.out.println("\nCOURSE REMOVED FROM CATALOG SUCCESSFULLY!\n");
		} catch (CourseNotFoundException | CourseNotDeletedException e) {
			System.out.println(e.getMessage());
		}
		return;
	}

	public Course viewCourseDetails(String courseCode) {
		Course c = null;
		try {
			c = adi.viewCourseDetails(courseCode);
		} catch (CourseNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return c;
	}

	public void calculateCpi(String studentid) {
		double cpi = 0.0;
		try {
			cpi = adi.calculateCpi(studentid);
			System.out.print("\nTHE CPI FOR STUDENT WITH STUDENT ID \""+studentid+"\" IS "+cpi+"\n");
		} catch (StudentNotRegisteredException e) {
			System.out.println(e.getMessage());
		}
	}

	public void generateReportCard(String studentId) {
//		System.out.println("\nREPORT CARD FOR STUDENT (STUDENT ID: "+studentId+")\n");
//		Student currStudent = UserData.students.get(studentId);
//		HashMap<String, RegisteredCourse> hmp = currStudent.getRegCourses();
//		System.out.println("Course Code\tCourse Name\tGrade Awarded\n");
//		for(Map.Entry<String, RegisteredCourse> m:hmp.entrySet())
//		{
//			RegisteredCourse rc = m.getValue();
//			System.out.println(rc.getCourseCode()+"\t"+rc.getName()+"\t"+rc.getGrade());
//		}
	}
}

//	public void generateReportCard(int adminId, int studentId, String courseCode, int semester)
//	{
//		Scanner sc = new Scanner(System.in);
//		System.out.print("\nEnter Grade Received by Student (Student Id: "+studentId+") in Course (Course Code: "+courseCode+"): ");
//		String grade = sc.next();
//		Student s = UserData.students.get(studentId);
//		s.get
//		System.out.println("Report Card Generated!");
//	}
