/**
 * 
 */
package com.signify.client;
import java.util.*;

import com.signify.jdbc.AdminDAOImplementation;
import com.signify.jdbc.UserData;
import com.signify.service.AdminInterface;
import com.signify.service.AdminServiceOperation;
/**
 * @author dp201
 *
 */
public class CRSAdminApplicationMenu {
	public static void main(String[] args) {
	}
	
	public void displayAdminMenu(int adminId)
	{
		System.out.println("\nWELCOME TO ADMIN MENU");
		System.out.println("=========================");
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("PRESS 1 FOR APPROVING STUDENT\nPRESS 2 TO ADD PROFESSOR\nPRESS 3 TO ADD ADMIN\nPRESS 4 TO GENERATE REPORT CARD\nPRESS 5 TO ASSIGN PROFESSOR TO COURSE\nPRESS 6 TO ADD COURSE\nPRESS 7 TO REMOVE COURSE\nPRESS 8 TO VIEW COURSE DETAILS\nPRESS 9 TO CALCULATE CPI\nPRESS 10 TO VIEW PROFESSORS\nPRESS 11 TO VIEW ADMINS\nPRESS 12 TO LOGOUT");
			int choice = sc.nextInt();
			AdminInterface as = new AdminServiceOperation();
			switch(choice)
			{
				case 1:
				{
					as.approveStudent();
					break;
				}
				case 2:
				{
					as.addProfessor();
					break;
				}
				case 3:
				{
					as.addAdmin();
					break;
				}
				case 4:
				{
					System.out.print("\nEnter Student ID: ");
					int sId = sc.nextInt();
//					System.out.print("Enter Course Code: ");
//					String courseCode = sc.nextLine();
//					System.out.print("Enter Semester: ");
//					int sem = sc.nextInt();
//					as.generateReportCard(adminId, sId, courseCode, sem);
					as.generateReportCard(sId);
					break;
				}
				case 5:
				{
					System.out.print("Enter Professor Id: ");
					int pId = sc.nextInt();
					System.out.print("Enter Course Code: ");
					sc.nextLine();
					String courseCode = sc.nextLine();
					as.assignProfessorToCourse(pId, courseCode);
					break;
				}
				case 6:
				{
					as.addCourse();
					break;
				}
				case 7:
				{
					System.out.print("Enter Course Code: ");
					sc.nextLine();
					String courseCode = sc.nextLine();
					as.removeCourse(courseCode);
					break;
				}
				case 8:
				{
					System.out.print("Enter Course Code: ");
					sc.nextLine();
					String courseCode = sc.nextLine();
					as.viewCourseDetails(courseCode);
					break;
				}
				case 9:
				{
					System.out.print("Enter Student Id: ");
					int sId = sc.nextInt();
					if(UserData.students.get(sId) == null)
					{
						System.out.println("\nNo student found with that id!\n");
						break;
					}
					double cpi = as.calculateCpi(sId);
					System.out.println("\nThe CPI for current semester is: "+cpi+"\n");
					break;
				}
				case 10:
				{
					as.viewProfessors();
					break;
				}
				case 11:
				{
					as.viewAdmins();
					break;
				}		
				case 12:
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
