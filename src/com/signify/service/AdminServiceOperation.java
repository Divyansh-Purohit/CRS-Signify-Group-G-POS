package com.signify.service;
import com.signify.bean.*;
import com.signify.collection.UserData;
import helper.*;
import java.time.LocalDate;
import java.util.*;
/**
 * @author dp201
 *
 */
public class AdminServiceOperation extends UserServiceOperation implements AdminInterface{
	
	public void approveStudent(int adminId)
	{
		Scanner sc = new Scanner(System.in);
		ArrayList<Student> unapprovedstudentsList = new ArrayList<Student>();
		for(Map.Entry<Integer, Student> m:UserData.students.entrySet()){
			Student s = m.getValue();
			if (!s.isApproved())
			{
				unapprovedstudentsList.add(s);
			}
		}
		if(unapprovedstudentsList.size() == 0)
		{
			System.out.println("\nNo Student waiting for approval!\n");
			return;
		}
		else
		{
			System.out.println("\nList of Unapproved Students");
			System.out.println("===========================");
			System.out.println("Student Id\tStudent Name");
			for (Student value : unapprovedstudentsList) {         	    
	     	    System.out.println(value.getStudentId()+"\t\t"+value.getName());
	     	}
		}
		
		System.out.println("\nPRESS 1 TO APPROVE ALL STUDENTS\nPRESS 2 TO APPROVE SINGLE STUDENT\nPRESS 3 TO GO BACK");
		while(true)
		{
			int choice = sc.nextInt();
			switch(choice)
			{
				case 1:
				{
					for(Map.Entry<Integer, Student> m:UserData.students.entrySet()){
						Student s = m.getValue();
						s.setApproved(true);
					}
					System.out.println("\nAll students approved!\n");
					return;
				}
				case 2:
				{
					System.out.print("\nEnter Student Id: ");
					int sId = sc.nextInt();
					try {
						UserData.students.get(sId).setApproved(true);
					}
					catch(Exception e)
					{
						System.out.println("\nStudent couldn't be approved, please try again!\n");
						return;
					}
					System.out.println("\nStudent "+sId+" Approved Successfully!\n");
					break;
				}
				case 3:
				{
					return;
				}
				default:
				{
					System.out.println("\nInvalid Input Received!\n");
					break;
				}
			}
		}
	}
	
	public void addProfessor(int adminId)
	{
		String[] userDetails = UserAdditionHelper.detailsHelper();
		String name = userDetails[0];
		String address = userDetails[1];
		String password = userDetails[2];
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Designation: ");
		String designation = sc.nextLine();
		System.out.print("Enter Department: ");
		String department = sc.nextLine();
		Professor newProfessor = new Professor();
		newProfessor.setName(name);
		newProfessor.setAddress(address);
		newProfessor.setDesignation(designation);
		newProfessor.setDepartment(department);
		newProfessor.setPassword(password);
		newProfessor.setCourseTaught(null);
		newProfessor.setUserId(Ids.userId++);
		
		try {
			UserData.professors.put(newProfessor.getUserId(), newProfessor);
		}
		catch(Exception e)
		{
			System.out.println("\nNew professor couldn't be added, please try again!\n");
			return;
		}
		
		System.out.println("\nNew Professor Added Successfully!\n");
	}
	
	public void viewProfessors(int adminId)
	{
		if(UserData.professors.size() == 0)
		{
			System.out.println("\nNo Professors Found!\n");
			return;
		}
		System.out.println("\nName\t\t\tDepartment\t\t\tDesignation");
		for(Map.Entry<Integer, Professor> m:UserData.professors.entrySet()){
			Professor c = m.getValue();
			System.out.println(c.getName()+"\t\t"+c.getDepartment()+"\t\t"+c.getDesignation());
		}	
	}
	
	public void assignProfessorToCourse(int adminId, int professorId, String courseCode)
	{
		Course currCourse = UserData.courses.get(courseCode);
		Professor currProfessor = UserData.professors.get(professorId);
		
		try {
			currCourse.setInstructor(professorId);
		}
		catch(Exception e)
		{
			System.out.println("\nProfessor couldn't be assigned to course, please try again.\n");
			return;
		}
		currProfessor.setCourseTaught(courseCode);
		System.out.println("\nProfessor Assigned to Course Successfully!\n");
	}
	
	public void addAdmin(int adminId)
	{
		String[] userDetails = UserAdditionHelper.detailsHelper();
		String name = userDetails[0];
		String address = userDetails[1];
		String password = userDetails[2];
		LocalDate doj = LocalDate.now();
		
		Admin newAdmin = new Admin();
		newAdmin.setUserId(Ids.userId++);
		newAdmin.setName(name);
		newAdmin.setAddress(address);
		newAdmin.setPassword(password);
		newAdmin.setDoj(doj);
		
		try {
			UserData.admins.put(newAdmin.getUserId(), newAdmin);
		}
		catch(Exception e)
		{
			System.out.println("\nNew admin couldn't be added, please try again!\n");
			return;
		}		
		System.out.println("\nNew Admin Added Successfully!\n");
	}
			
	public void viewAdmins(int adminId)
	{
		if(UserData.admins.size() == 0)
		{
			System.out.println("\nNo Admins Found!\n");
			return;
		}
		System.out.println("\nAdmins");
		for(Map.Entry<Integer, Admin> m:UserData.admins.entrySet()){
			Admin a = m.getValue();
			System.out.println(a.getName());
		}	
	}
	
	
	public void addCourse(int adminId)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Course Code: ");
		String courseCode = sc.nextLine();
		System.out.print("Enter Course Name: ");
		String courseName = sc.nextLine();
		System.out.print("Enter Course Instructor's Id: ");
		int courseInstructor = sc.nextInt();
		System.out.print("Is Course Offered: ");
		sc.nextLine();
		String cio = sc.nextLine();
		
		boolean courseIsOffered = cio.equals("yes") ? true : false;
		
		Course newCourse = new Course();
		newCourse.setCourseCode(courseCode);
		newCourse.setName(courseName);
		newCourse.setInstructor(courseInstructor);
		newCourse.setOffered(courseIsOffered);
		newCourse.setNumStudents(0);
		newCourse.setSeatsAvailable(10);
		
		try {
			UserData.courses.put(newCourse.getCourseCode(), newCourse);
		}
		catch(Exception e)
		{
			System.out.println("\nCourse could not be added, please try again!\n");
			return;
		}
		
		System.out.println("\nCourse Added Successfully!\n");
	}
	
	
	public void removeCourse(int adminId, String courseCode)
	{
		try
		{
			UserData.courses.remove(courseCode);
		}
		catch(Exception e)
		{
			System.out.println("\nCourse could not be removed, please try again!");
			return;
		}
		System.out.println("\nCourse Removed Successfully!\n");
	}
	
	
	public void viewCourseDetails(int adminId, String courseCode)
	{
		Course currCourse;
		try {
			currCourse = UserData.courses.get(courseCode);
		}
		catch(Exception e)
		{
			System.out.println("\nCourse details couldn't be viewed, please try again!");
			return;
		}
		if(currCourse == null) {
			System.out.println("\nInvalid Course Code Entered!\n");
			return;
		}
		int instructorId = currCourse.getInstructor();
		Professor currProfessor = UserData.professors.get(instructorId);
		String instructorName = currProfessor.getName();
		System.out.println("\nCourse Code\tCourse Name\tCourse Instructor\tStudents Enrolled\tOffered");
		System.out.println("\n"+currCourse.getCourseCode()+"\t\t"+currCourse.getName()+"\t\t"+instructorName+"\t\t"+currCourse.getNumStudents()+"\t\t"+currCourse.isOffered());
		
	}

	public double calculateCpi(int adminId, int studentId)
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
	public void generateReportCard(int adminId, int studentId)
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

