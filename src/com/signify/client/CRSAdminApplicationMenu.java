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

/*
 * Class that display Admin Client Menu:-
 * Approving student
 * Add professor
 * Add Admin
 * Assign Professor to course
 * Add Course
 * Remove Course
 * View course details
 * Calculate CPI
 * View Professors
 * View Admins
 * Logout
 */
public class CRSAdminApplicationMenu {
	/**
	 * 
	 * @param adminid
	 */
	public void displayAdminMenu(String adminid) {
		System.out.println("\nWELCOME TO ADMIN MENU");
		System.out.println("=========================");
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		while (true) {
			System.out.println(
					"PRESS 1 FOR APPROVING STUDENT\nPRESS 2 TO ADD PROFESSOR\nPRESS 3 TO ADD ADMIN\nPRESS 4 TO ASSIGN PROFESSOR TO COURSE\nPRESS 5 TO ADD COURSE\nPRESS 6 TO REMOVE COURSE\nPRESS 7 TO VIEW COURSE DETAILS\nPRESS 8 TO CALCULATE CPI\nPRESS 9 TO VIEW PROFESSORS\nPRESS 10 TO VIEW ADMINS\nPRESS 11 TO LOGOUT\n");

			try {
				choice = sc.nextInt();
			} catch (InputMismatchException e) {
				sc.nextLine();
				choice = 0;
			}
			AdminInterface as = new AdminServiceOperation();
			switch (choice) {
			case 1: {
				List<Student> students = as.listOfUnapprovedStudents();
				System.out.println("\nLIST OF UNAPPROVED STUDENTS\n\nUSER ID STUDENT ID STUDENT NAME\n");
				for (Student x : students) {
					System.out.println(x.getUserId() + "\t" + x.getStudentid() + "\t" + x.getName());

				}
				outer: while (true) {
					System.out.print(
							"\nPRESS 1 TO APPROVE ALL STUDENTS\nPRESS 2 TO APPROVE STUDENT BY ID\nPRESS 3 TO GO BACK\n");
					int cha = sc.nextInt();
					switch (cha) {
					case 1: {
						as.approveAllStudents();
						break;
					}
					case 2: {
						sc.nextLine();
						System.out.print("\nENTER STUDENT ID: ");
						String studentid = sc.nextLine();
						as.approveStudentById(studentid);
						break;
					}
					case 3: {
						break outer;
					}
					default: {
						System.out.println("\nINVALID INPUT RECEIVED!\n");
						break;
					}
					}
				}
				break;
			}
			case 2: {
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
				System.out.print("ENTER DEPARTMENT: ");
				String dept = sc.nextLine();
				System.out.print("ENTER DESIGNATION: ");
				String desig = sc.nextLine();
				newProfessor.setDepartment(dept);
				newProfessor.setDesignation(desig);
				newProfessor.setCourseTaught("NA");
				as.addProfessor(newProfessor);
				break;
			}
			case 3: {
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
				break;
			}
//			case 4: {
//				System.out.print("\nEnter Student ID: ");
//				String studentid = sc.nextLine();
//				as.generateReportCard(studentid);
//				System.out.println();
//				break;
//			}
			case 4: {
				sc.nextLine();
				System.out.print("\nEnter Professor Id: ");
				String professorid = sc.nextLine();
				System.out.print("Enter Course Code: ");
				String courseCode = sc.nextLine();
				as.assignProfessorToCourse(professorid, courseCode);
				break;
			}
			case 5: {
				sc.nextLine();
				System.out.print("\nEnter Course Code: ");
				String courseCode = sc.nextLine();
				System.out.print("Enter Course Name: ");
				String coursename = sc.nextLine();
				System.out.print("Enter Course Instructor: ");
				String instructor = sc.nextLine();
				double courseFee = 0.0;
				while (true) {
					System.out.print("Enter Course Fee: ");
					try {
						courseFee = sc.nextDouble();
						break;
					} catch (InputMismatchException e) {
						System.out.println("\nINVALID AMOUNT RECEIVED!\n");
						sc.nextLine();
					}
				}
				int sem = 0;
				while (true) {
					System.out.print("Enter Semester: ");
					try {
						sem = sc.nextInt();
						break;
					} catch (InputMismatchException e) {
						System.out.println("\nINVALID VALUE FOR SEMESTER RECEIVED!\n");
						sc.nextLine();
					}
				}
				Course c = new Course();
				c.setCourseCode(courseCode);
				c.setName(coursename);
				c.setInstructor(instructor);
				c.setFee(courseFee);
				c.setNumStudents(0);
				c.setSemester(sem);
				as.addCourse(c);
				break;
			}
			case 6: {
				System.out.print("Enter Course Code: ");
				sc.nextLine();
				String courseCode = sc.nextLine();
				as.removeCourse(courseCode);
				break;
			}
			case 7: {
				sc.nextLine();
				System.out.print("\nEnter Course Code: ");
				String courseCode = sc.nextLine();
				Course c = as.viewCourseDetails(courseCode);
				if (!(c == null)) {
					System.out.println("\nCourse Code\tCourse Name\tInstructor\tCourse Fee\tSemester\n");
					System.out.println(c.getCourseCode() + "\t" + c.getName() + "\t" + c.getInstructor() + "\t"
							+ c.getFee() + "\t" + c.getSemester());
					System.out.println("\nCOURSE DETAILS VIEWED!\n");
				}

				break;
			}
			case 8: {
				sc.nextLine();
				System.out.print("\nEnter Student Id: ");
				String studentid = sc.nextLine();
				as.calculateCpi(studentid);
				break;
			}
			case 9: {
				List<Professor> p = as.viewProfessors();
				if(p.size() == 0)
				{
					System.out.println("\nNO ADMINS FOUND IN THE DATABASE!\n");
					break;
				}
				System.out.println("Professor Id\tProfesssor Name\t\tDepartment\tDesignation\tCourse Taught\n");
				for (Professor x : p) {
					System.out.println(x.getUserId() + "\t" + x.getName() + "\t" + x.getDepartment() + "\t"
							+ x.getDesignation() + "\t" + x.getCourseTaught());
				}
				System.out.println();
				break;
			}
			case 10: {
				List<Admin> a = as.viewAdmins();
				if(a.size() == 0)
				{
					System.out.println("\nNO ADMINS FOUND IN THE DATABASE!\n");
					break;
				}
				System.out.println("Admin Id\t\t\tAdmin Name\n");
				for (Admin x : a) {
					System.out.println(x.getUserId() + "\t" + x.getName());
				}
				System.out.println();
				break;
			}
			case 11: {
				return;
			}
			default: {
				System.out.println("\nINVALID INPUT RECEIVED!\n");
			}
			}
		}
	}

}
