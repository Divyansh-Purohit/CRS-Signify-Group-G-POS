package com.signify.service;
import com.signify.bean.*;
import com.signify.collection.UserData;
import com.signify.jdbc.AdminDAOImplementation;

import helper.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 * @author dp201
 *
 */
public class AdminServiceOperation extends UserServiceOperation implements AdminInterface{
	
	
	public AdminDAOImplementation adi = new AdminDAOImplementation();
	
	public List<Student> listOfUnapprovedStudents()
	{	
		return adi.listOfUnapprovedStudents();
		
	}
	public void approveAllStudents()
	{
		adi.approveAllStudents();
		return;
	}
	
	public void approveStudentById(String studentid)
	{
		adi.approveStudentById(studentid);
		return;
	}
	public void addProfessor(Professor newProfessor)
	{
		adi.addProfessor(newProfessor);
		return;
	}
	public List<Professor> viewProfessors()
	{
		return adi.viewProfessors();	
	}
	public List<Admin> viewAdmins()
	{
		return adi.viewAdmins();
	}	
	public void assignProfessorToCourse(String professorId, String courseCode)
	{
		adi.assignProfessorToCourse(professorId, courseCode);
	}
	
	public void addAdmin(Admin newAdmin)
	{
		adi.addAdmin(newAdmin);
		return;
	}
				
	public void addCourse(Course c)
	{
		adi.addCourse(c);
		return;
	}
	
	public void removeCourse(String courseCode)
	{
		adi.removeCourse(courseCode);
		return;
	}
	public Course viewCourseDetails(String courseCode)
	{
		return adi.viewCourseDetails(courseCode);		
	}
	public double calculateCpi(String studentid)
	{
		return adi.calculateCpi(studentid);
	}
	public void generateReportCard(String studentId)
	{
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

