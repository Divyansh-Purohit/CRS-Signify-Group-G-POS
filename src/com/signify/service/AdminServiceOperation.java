package com.signify.service;
import com.signify.bean.*;
import com.signify.collection.UserData;
import com.signify.jdbc.AdminDAOImplementation;

import helper.*;
import java.time.LocalDate;
import java.util.*;
/**
 * @author dp201
 *
 */
public class AdminServiceOperation extends UserServiceOperation implements AdminInterface{
	
	
	public AdminDAOImplementation adi = new AdminDAOImplementation();
	
	public void approveStudent()
	{
//		AdminDAOImplementation adi = new AdminDAOImplementation();
		adi.approveStudent();
	}
	
	public void addProfessor()
	{
		Scanner sc = new Scanner(System.in);
		String[] userDetails = UserAdditionHelper.detailsHelper();
		String name = userDetails[0];
		String address = userDetails[1];
		String password = userDetails[2];
		System.out.print("Enter Designation: ");
		String designation = sc.nextLine();
		System.out.print("Enter Department: ");
		String department = sc.nextLine();
//		AdminDAOImplementation adi = new AdminDAOImplementation();
		adi.addProfessor(name, password, address, designation, department);
		System.out.println("\nNew Professor Added Successfully!\n");
	}
	
	public void viewProfessors()
	{
//		AdminDAOImplementation adi = new AdminDAOImplementation();
		adi.viewProfessors();	
	}
	
	public void assignProfessorToCourse(int professorId, String courseCode)
	{
//		AdminDAOImplementation adi = new AdminDAOImplementation();
		adi.assignProfessorToCourse(professorId, courseCode);
		System.out.println("\nProfessor Assigned to Course Successfully!\n");
	}
	
	public void addAdmin()
	{
		String[] userDetails = UserAdditionHelper.detailsHelper();
		String name = userDetails[0];
		String address = userDetails[1];
		String password = userDetails[2];
//		AdminDAOImplementation adi = new AdminDAOImplementation();
		adi.addAdmin(name, password, address);
		System.out.println("\nNew Admin Added Successfully!\n");
	}
			
	public void viewAdmins()
	{
//		AdminDAOImplementation adi = new AdminDAOImplementation();
		adi.viewAdmins();
		return;
	}	
	
	public void addCourse()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Course Code: ");
		String courseCode = sc.nextLine();
		System.out.print("Enter Course Name: ");
		String courseName = sc.nextLine();
		System.out.print("Enter Course Instructor's Id: ");
		int courseInstructor = sc.nextInt();
//		AdminDAOImplementation adi = new AdminDAOImplementation();
		adi.addCourse(courseCode, courseName, courseInstructor);
		System.out.println("\nCourse Added Successfully!\n");
		return;
	}
	
	
	public void removeCourse(String courseCode)
	{
//		AdminDAOImplementation adi = new AdminDAOImplementation();
		adi.removeCourse(courseCode);
		System.out.println("\nCourse Removed Successfully!\n");
		return;
	}
	
	
	public void viewCourseDetails(String courseCode)
	{
//		AdminDAOImplementation adi = new AdminDAOImplementation();
		adi.viewCourseDetails(courseCode);
		return;		
	}

	public double calculateCpi(int studentId)
	{
		Student s = UserData.students.get(studentId);
		double cgpa = 0.0;
		for(Map.Entry<String, RegisteredCourse> m:s.getRegCourses().entrySet()){
			RegisteredCourse rc = m.getValue();
			if(rc.getGrade().equals("A+") || rc.getGrade().equals("A"))
				cgpa += 9.0;
			else if(rc.getGrade().equals("A-"))
				cgpa += 8.5;
			else if(rc.getGrade().equals("B"))
				cgpa += 8.0;
			else if(rc.getGrade().equals("B-"))
				cgpa += 7.5;
			else if(rc.getGrade().equals("C"))
				cgpa += 7.0;
			else if(rc.getGrade().equals("C-"))
				cgpa += 6.5;
			else if(rc.getGrade().equals("D"))
				cgpa += 6.0;
			else 
				cgpa += 0;
		}
		return cgpa/s.getNumRegCourses();
	}
	public void generateReportCard(int studentId)
	{
		System.out.println("\nREPORT CARD FOR STUDENT (STUDENT ID: "+studentId+")\n");
		Student currStudent = UserData.students.get(studentId);
		HashMap<String, RegisteredCourse> hmp = currStudent.getRegCourses();
		System.out.println("Course Code\tCourse Name\tGrade Awarded\n");
		for(Map.Entry<String, RegisteredCourse> m:hmp.entrySet())
		{
			RegisteredCourse rc = m.getValue();
			System.out.println(rc.getCourseCode()+"\t"+rc.getName()+"\t"+rc.getGrade());
		}
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

