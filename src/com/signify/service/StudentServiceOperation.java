package com.signify.service;
import com.signify.collection.UserData;
import com.signify.bean.*;
import helper.*;
import java.time.LocalDate;
import java.util.*;
/**
 * @author dp201
 *
 */

public class StudentServiceOperation extends UserServiceOperation implements StudentInterface{
	
	public void register()
	{	
		Scanner sc = new Scanner(System.in);
		String[] userDetails = UserAdditionHelper.detailsHelper();
		String username = userDetails[0];
		String address = userDetails[1];
		String password = userDetails[2];
		System.out.print("Enter Semester: ");
		int semester = sc.nextInt();
		LocalDate doj = LocalDate.now();
		
		Student newStudent = new Student();
		newStudent.setName(username);
		newStudent.setAddress(address);
		newStudent.setPassword(password);
		newStudent.setSemester(semester);
		newStudent.setDateOfRegistration(doj);
		newStudent.setStudentId(Ids.studentId++);
		newStudent.setUserId(Ids.userId++);
		newStudent.setApproved(false);
		newStudent.setNumRegCourses(0);
						
		try
		{
			UserData.students.put(newStudent.getStudentId(), newStudent);
		}
		catch(Exception e)
		{
			System.out.println("\nFailed to Register, please try again!");
		}
	    System.out.println("\nStudent Registration Successful! Waiting for approval from admin.");
	}
	
	public void viewGrades(int studentId)
	{
		Student currStudent = UserData.students.get(studentId);
		if(currStudent.getNumRegCourses() == 0)
		{
			System.out.println("\nNo Courses Found!\n");
			return;
		}
		HashMap<String, RegisteredCourse> rg = currStudent.getRegCourses();
		
		System.out.println("\nCourse Code\tCourse Name\tGrade Awarded\n");
		for(Map.Entry<String, RegisteredCourse> m: rg.entrySet()) {
			RegisteredCourse c = m.getValue();
			System.out.println(c.getCourseCode()+"\t"+c.getName()+"\t"+c.getGrade());
		}
		
		System.out.println("\nAll Registered Courses Grades Viewed!\n");
	}
	
	public void viewGrade(int studentId, String courseId)
	{
		Student currStudent = UserData.students.get(studentId);
		if(currStudent.getNumRegCourses() == 0)
		{
			System.out.println("\nNo Courses Found!\n");
			return;
		}

		HashMap<String, RegisteredCourse> rg = currStudent.getRegCourses();
		System.out.println("\nCourse Code\tCourse Name\tGrade Awarded\n");
		for(Map.Entry<String, RegisteredCourse> m: rg.entrySet()) {
			RegisteredCourse c = m.getValue();
			if(c.getCourseCode().equals(courseId))
				System.out.println(c.getCourseCode()+"\t"+c.getName()+"\t"+c.getGrade());
			else 
				continue;
		}
		System.out.println("Course Grade Viewed!");
	}
		
	public void viewRegisterCourses(int studentId)
	{	
		System.out.println("\nList of courses you're registered in");
		System.out.println("==========================\n");
		System.out.println("Course Code\tCourse Name");
		Student currStudent = UserData.students.get(studentId);
		HashMap<String, RegisteredCourse> rg = currStudent.getRegCourses();
		for(Map.Entry<String, RegisteredCourse> m:rg.entrySet()) {
			RegisteredCourse r = m.getValue();
			System.out.println(r.getCourseCode()+"\t"+r.getName());
		}
		System.out.println();
		return;
	}
	
	public void addCourse(int studentId)
	{	
		Student currStudent = UserData.students.get(studentId);
		if(currStudent.getNumRegCourses() == 4)
		{
			System.out.println("\nYou can't register up for more courses!\n");
			return;
		}
		System.out.println("\nChoose from one of available courses");
		System.out.println("====================================");
		System.out.println("Course Code\tCourse Name\tAvailable Seats");
		for(Map.Entry<String, Course> m:UserData.courses.entrySet())
		{  
			Course c = m.getValue();
			if(c.getSeatsAvailable() <= 9)
			{
				System.out.println(m.getKey()+"\t"+c.getName()+"\t"+c.getSeatsAvailable());
			}
			else
				continue;
		} 
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		System.out.println("===================");
		System.out.print("\nEnter Course Id: ");
		String c_id = sc.nextLine();
		
		Course intCourse = UserData.courses.get(c_id);
		intCourse.setSeatsAvailable(intCourse.getSeatsAvailable()-1);
		intCourse.setNumStudents(intCourse.getNumStudents()+1);
		
		RegisteredCourse rg = new RegisteredCourse();
		rg.setCourseCode(c_id);
		rg.setName(intCourse.getName());
		rg.setSemester(currStudent.getSemester());
		rg.setGrade("NA");
		
		try {
			HashMap<String, RegisteredCourse> mp = currStudent.getRegCourses();
			mp.put(c_id, rg);
		}
		catch(Exception e)
		{
			System.out.println("\nCouldn't register in course, please try again!\n");
			return;
		}
		
		HashMap<Integer, String> eS = intCourse.getEnrolledStudents();
		eS.put(studentId, currStudent.getName());
		intCourse.setEnrolledStudents(eS);
		currStudent.setNumRegCourses(currStudent.getNumRegCourses()+1);
		System.out.println("\nEnrolled in Course (Course Id: "+c_id+")!\n");
	}
	
	public void dropCourse(int studentId, String courseId)
	{
		Student currStudent = UserData.students.get(studentId);
		if(currStudent.getNumRegCourses() == 0)
		{
			System.out.println("\nYou haven't registerd for any course!\n");
			return;
		}
		HashMap<String, RegisteredCourse> mp = currStudent.getRegCourses();
		try {
			mp.remove(courseId);
		}
		catch(Exception e)
		{
			System.out.println("\nCouldn't drop course at the moment. please try again!\n");
			return;
		}
		Course currCourse = UserData.courses.get(courseId);
		currCourse.setNumStudents(currCourse.getNumStudents()-1);
		currCourse.setSeatsAvailable(currCourse.getSeatsAvailable()+1);
		currStudent.setNumRegCourses(currStudent.getNumRegCourses()-1);
		System.out.println("\nCourse (Course Id: "+courseId+") Dropped Successfully!\n");
	}
	
	public void payFees(int studentId)
	{	
		Scanner sc = new Scanner(System.in);
		int choice;
		while(true)
		{
			System.out.println("PRESS 1 FOR OFFLINE PAYMENT\nPRESS 2 FOR ONLINE PAYMENT\nPRESS 3 IF SCHOLARSHIP RECEIVED\nPRESS 4 TO GO BACK\n");
			choice = sc.nextInt();
			switch(choice)
			{
				case 1:
				{
					int mode;
					while(true)
					{
						System.out.print("PRESS 1 FOR CASH PAYMENT\nPRESS 2 FOR CHEQUE PAYMENT\nPRESS 3 TO GO BACK\n");
						mode = sc.nextInt();
						switch(mode)
						{
							case 1:
							{
								return;
							}
							case 2:
							{
								System.out.print("ENTER BANK NAME: ");
								sc.nextLine();
								String bankName = sc.nextLine(); 
								System.out.print("ENTER CHEQUE NUMBER: ");
								int chequeNumber = sc.nextInt();
								return;
							}
							case 3:
							{
								return;
							}
							default:
							{
								System.out.println("\nInvalid Input Received!\n");
							}
						}
					}
				}
				case 2:
				{
					int mode;
					while(true)
					{
						System.out.print("PRESS 1 FOR CARD PAYMENT\nPRESS 2 FOR PAYMENT THROUGH NET BANKING\nPRESS 3 TO GO BACK\n");
						mode = sc.nextInt();
						switch(mode)
						{
							case 1:
							{
								System.out.print("ENTER CARD NUMBER: ");
								sc.nextLine();
								String cardNumber = sc.nextLine(); 
								System.out.print("ENTER CARD TYPE: ");
								String cardType = sc.nextLine();
								return;
							}
							case 2:
							{
								System.out.print("ENTER MODE of TRANSFER: ");
								sc.nextLine();
								String modeOfTransfer = sc.nextLine(); 
								System.out.print("ENTER ACCOUNT NUMBER: ");
								String accountNumber = sc.nextLine();
								System.out.print("ENTER IFS CODE: ");
								String ifsCode = sc.nextLine();
								return;
							}
							case 3:
							{
								return;
							}
							default:
							{
								System.out.println("\nInvalid Input Received!\n");
							}
						}
					}				
				}
				case 3:
				{
					System.out.print("Enter Scholarship Name: ");
					sc.nextLine();
					String schname = sc.nextLine();
					return;
				}
				case 4:
				{
					return;
				}
				default:
				{
					System.out.println("\nInvalid Input Received!\n");
				}
			}
		}
	}
	
}
