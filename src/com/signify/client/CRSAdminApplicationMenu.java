/**
 * 
 */
package com.signify.client;
import java.util.*;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.bean.User;
import com.signify.helper.UserRegistration;
import com.signify.service.AdminInterface;
import com.signify.service.AdminServiceOperation;
/**
 * @author dp201
 *
 */
public class CRSAdminApplicationMenu {
	
	public void displayAdminMenu(String adminid)
	{
		System.out.println("\nWELCOME TO ADMIN MENU");
		System.out.println("=========================");
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("PRESS 1 FOR APPROVING STUDENT\nPRESS 2 TO ADD PROFESSOR\nPRESS 3 TO ADD ADMIN\nPRESS 4 TO GENERATE REPORT CARD\nPRESS 5 TO ASSIGN PROFESSOR TO COURSE\nPRESS 6 TO ADD COURSE\nPRESS 7 TO REMOVE COURSE\nPRESS 8 TO VIEW COURSE DETAILS\nPRESS 9 TO CALCULATE CPI\nPRESS 10 TO VIEW PROFESSORS\nPRESS 11 TO VIEW ADMINS\nPRESS 12 TO LOGOUT\n");
			int choice = sc.nextInt();
			AdminInterface as = new AdminServiceOperation();
			switch(choice)
			{
				case 1:
				{
					List<Student> students = as.listOfUnapprovedStudents();
					System.out.println("\nLIST OF UNAPPROVED STUDENTS\n\nUSER ID STUDENT ID STUDENT NAME\n");
					for(Student x: students)
					{
						System.out.println(x.getUserId() +"\t" + x.getStudentid() +"\t" + x.getName());
						
					}
					outer: while(true)
					{
						System.out.print("\nPRESS 1 TO APPROVE ALL STUDENTS\nPRESS 2 TO APPROVE STUDENT BY ID\nPRESS 3 TO GO BACK\n");
						int cha = sc.nextInt();
						switch(cha)
						{
							case 1:
							{
								as.approveAllStudents();
								break;
							}
							case 2:
							{
								sc.nextLine();
								System.out.println("\nENTER STUDENT ID: ");
								String studentid = sc.nextLine();
								as.approveStudentById(studentid);
								break;
							}
							case 3:
							{
								break outer;
							}
							default:
							{
								System.out.println("\nINVALID INPUT RECEIVED!\n");
								break;
							}
						}
					}
					break;
				}
				case 2:
				{
					UserRegistration ur = new UserRegistration();
					User newUser = ur.registerUser(3);
					Professor newProfessor = new Professor();
					newProfessor.setUserId(newUser.getUserId());
					newProfessor.setName(newUser.getName());
					newProfessor.setPassword(newUser.getPassword());
					newProfessor.setAddress(newUser.getAddress());
					newProfessor.setDoj(newUser.getDoj());
					newProfessor.setRoleid(newUser.getRoleid());
					sc.nextLine();
					System.out.print("\nENTER DEPARTMENT: ");
					String dept = sc.nextLine();			
					System.out.print("ENTER DESIGNATION: ");
					String desig = sc.nextLine();
					newProfessor.setDepartment(dept);
					newProfessor.setDesignation(desig);
					newProfessor.setCourseTaught("NA");
					as.addProfessor(newProfessor);
					System.out.println("\nNEW PROFESSOR ADDED TO DB SUCCESSFULLY!\n");
					break;
				}
				case 3:
				{
					UserRegistration ur = new UserRegistration();
					User newUser = ur.registerUser(2);
					Admin newAdmin = new Admin();
					newAdmin.setUserId(newUser.getUserId());
					newAdmin.setName(newUser.getName());
					newAdmin.setPassword(newUser.getPassword());
					newAdmin.setAddress(newUser.getAddress());
					newAdmin.setDoj(newUser.getDoj());
					newAdmin.setRoleid(newUser.getRoleid());
					as.addAdmin(newAdmin);
					System.out.println("\nNEW ADMIN ADDED TO DB SUCCESSFULLY!\n");
					break;
				}
				case 4:
				{
					System.out.print("\nEnter Student ID: ");
					String studentid = sc.nextLine();
					as.generateReportCard(studentid);
					System.out.println();
					break;
				}
				case 5:
				{
					sc.nextLine();
					System.out.print("\nEnter Professor Id: ");
					String professorid = sc.nextLine();
					System.out.print("Enter Course Code: ");
					sc.nextLine();
					String courseCode = sc.nextLine();
					as.assignProfessorToCourse(professorid, courseCode);
					System.out.println("\npROFESSOR ASSIGNED TO COURSE SUCCESSFULLY!\n");
					break;
				}
				case 6:
				{
					sc.nextLine();
					System.out.print("\nEnter Course Code: ");
					String courseCode = sc.nextLine();
					System.out.print("Enter Course Name: ");
					String coursename = sc.nextLine();
					System.out.print("Enter Course Instructor: ");
					String instructor = sc.nextLine();
					System.out.print("Enter Course Fee: ");
					double courseFee = sc.nextDouble();
					Course c = new Course();
					c.setCourseCode(courseCode);
					c.setName(coursename);
					c.setInstructor(instructor);
					c.setFee(courseFee);
					c.setNumStudents(0);
//					c.isOffered()
					as.addCourse(c);
					System.out.println("\nCOURSE ADDED TO CATALOG SUCCESSFULLY!\n");
					break;
				}
				case 7:
				{
					System.out.print("Enter Course Code: ");
					sc.nextLine();
					String courseCode = sc.nextLine();
					as.removeCourse(courseCode);
					System.out.println("\nCOURSE REMOVED FROM CATALOG SUCCESSFULLY!\n");
					break;
				}
				case 8:
				{
					sc.nextLine();
					System.out.print("\nEnter Course Code: ");
					String courseCode = sc.nextLine();
					Course c = as.viewCourseDetails(courseCode);
					System.out.println("\nCourse Code\tCourse Name\tInstructor\tCourse Fee\n");
					System.out.println(c.getCourseCode()+"\t"+c.getName()+"\t"+c.getInstructor()+"\t"+c.getFee());
					System.out.println("\nCOURSE DETAILS VIEWED!\n");
					break;
				}
				case 9:
				{
					System.out.print("\nEnter Student Id: ");
					String studentid = sc.nextLine();
					double cpi = as.calculateCpi(studentid);
					System.out.println("\nThe CPI for current semester is: "+cpi+"\n");
					System.out.println();
					break;
				}
				case 10:
				{
					List<Professor> p = as.viewProfessors();
					System.out.println("Professor Id\tProfesssor Name\t\tDepartment\tDesignation\tCourse Taught\n");
					for(Professor x: p)
					{
						System.out.println(x.getUserId()+"\t"+x.getName()+"\t"+x.getDepartment()+"\t"+x.getDesignation()+"\t"+x.getCourseTaught());
					}
					System.out.println();
					break;
				}
				case 11:
				{
					List<Admin> a = as.viewAdmins();
					System.out.println("Admin Id\t\t\tAdmin Name\n");
					for(Admin x: a)
					{
						System.out.println(x.getUserId()+"\t"+x.getName());
					}
					System.out.println();
					break;
				}		
				case 12:
				{
					return;
				}
				default:
				{
					System.out.println("\nINVALID INPUT RECEIVED!\n");
				}
			}
		}
	}

}
