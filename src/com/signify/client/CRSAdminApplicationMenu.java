/**
 * 
 */
package com.signify.client;
import java.util.*;

import com.signify.service.AdminInterface;
import com.signify.service.AdminServiceOperation;
/**
 * @author dp201
 *
 */
public class CRSAdminApplicationMenu {
	public static void main(String[] args) {
		
	}
	
	public void displayAdminMenu()
	{
		System.out.println("WELCOME TO ADMIN MENU");
		System.out.println("=========================");
		Scanner sc = new Scanner(System.in);
		System.out.println("PRESS 1 FOR APPROVING STUDENT\nPRESS 2 TO ADD PROFESSOR\nPRESS 3 TO GENERATE REPORT CARD\nPRESS 4 TO ASSIGN PROFESSOR TO COURSE\nPRESS 5 TO ADD COURSE\nPRESS 6 TO REMOVE COURSE\nPRESS 7 TO VIEW COURSES\nPRESS 8 TO CALCULATE CPI\nPRESS 9 TO GO BACK");
		int choice = sc.nextInt();
		
//		AdminServiceOperation as = new AdminServiceOperation();
		AdminInterface as = new AdminServiceOperation();
		while(choice<=9)
		{
		
		switch(choice)
		{
		case 1:
		{
			as.approveStudent(0);
			System.out.println("PRESS 1 FOR APPROVING STUDENT\nPRESS 2 TO ADD PROFESSOR\nPRESS 3 TO GENERATE REPORT CARD\nPRESS 4 TO ASSIGN PROFESSOR TO COURSE\nPRESS 5 TO ADD COURSE\nPRESS 6 TO REMOVE COURSE\nPRESS 7 TO VIEW COURSES\nPRESS 8 TO CALCULATE CPI\nPRESS 9 TO GO BACK");
			choice = sc.nextInt();
			break;
		}
		case 2:
		{
			as.addProfessor(0);
			System.out.println("PRESS 1 FOR APPROVING STUDENT\nPRESS 2 TO ADD PROFESSOR\nPRESS 3 TO GENERATE REPORT CARD\nPRESS 4 TO ASSIGN PROFESSOR TO COURSE\nPRESS 5 TO ADD COURSE\nPRESS 6 TO REMOVE COURSE\nPRESS 7 TO VIEW COURSES\nPRESS 8 TO CALCULATE CPI\nPRESS 9 TO GO BACK");
			choice = sc.nextInt();
			break;
		}
		case 3:
		{
			as.generateReportCard(0, 0, 0);
			System.out.println("PRESS 1 FOR APPROVING STUDENT\nPRESS 2 TO ADD PROFESSOR\nPRESS 3 TO GENERATE REPORT CARD\nPRESS 4 TO ASSIGN PROFESSOR TO COURSE\nPRESS 5 TO ADD COURSE\nPRESS 6 TO REMOVE COURSE\nPRESS 7 TO VIEW COURSES\nPRESS 8 TO CALCULATE CPI\nPRESS 9 TO GO BACK");
			choice = sc.nextInt();
			break;
		}
		case 4:
		{
			as.assignProfessorToCourse(0, 0);
			System.out.println("PRESS 1 FOR APPROVING STUDENT\nPRESS 2 TO ADD PROFESSOR\nPRESS 3 TO GENERATE REPORT CARD\nPRESS 4 TO ASSIGN PROFESSOR TO COURSE\nPRESS 5 TO ADD COURSE\nPRESS 6 TO REMOVE COURSE\nPRESS 7 TO VIEW COURSES\nPRESS 8 TO CALCULATE CPI\nPRESS 9 TO GO BACK");
			choice = sc.nextInt();
			break;
		}
		case 5:
		{
			as.addCourse(0);
			System.out.println("PRESS 1 FOR APPROVING STUDENT\nPRESS 2 TO ADD PROFESSOR\nPRESS 3 TO GENERATE REPORT CARD\nPRESS 4 TO ASSIGN PROFESSOR TO COURSE\nPRESS 5 TO ADD COURSE\nPRESS 6 TO REMOVE COURSE\nPRESS 7 TO VIEW COURSES\nPRESS 8 TO CALCULATE CPI\nPRESS 9 TO GO BACK");
			choice = sc.nextInt();
			break;
		}
		case 6:
		{
			as.removeCourse(0);
			System.out.println("PRESS 1 FOR APPROVING STUDENT\nPRESS 2 TO ADD PROFESSOR\nPRESS 3 TO GENERATE REPORT CARD\nPRESS 4 TO ASSIGN PROFESSOR TO COURSE\nPRESS 5 TO ADD COURSE\nPRESS 6 TO REMOVE COURSE\nPRESS 7 TO VIEW COURSES\nPRESS 8 TO CALCULATE CPI\nPRESS 9 TO GO BACK");
			choice = sc.nextInt();
			break;
		}
		case 7:
		{
			as.viewCourseDetails(0);
			System.out.println("PRESS 1 FOR APPROVING STUDENT\nPRESS 2 TO ADD PROFESSOR\nPRESS 3 TO GENERATE REPORT CARD\nPRESS 4 TO ASSIGN PROFESSOR TO COURSE\nPRESS 5 TO ADD COURSE\nPRESS 6 TO REMOVE COURSE\nPRESS 7 TO VIEW COURSES\nPRESS 8 TO CALCULATE CPI\nPRESS 9 TO GO BACK");
			choice = sc.nextInt();
			break;
		}
		case 8:
		{
			as.calculateCpi(0);
			System.out.println("PRESS 1 FOR APPROVING STUDENT\nPRESS 2 TO ADD PROFESSOR\nPRESS 3 TO GENERATE REPORT CARD\nPRESS 4 TO ASSIGN PROFESSOR TO COURSE\nPRESS 5 TO ADD COURSE\nPRESS 6 TO REMOVE COURSE\nPRESS 7 TO VIEW COURSES\nPRESS 8 TO CALCULATE CPI\nPRESS 9 TO GO BACK");
			choice = sc.nextInt();
			break;
		}
		case 9:
		{
			CRSApplicationMenu crss = new CRSApplicationMenu();
			crss.loginHandler();
			break;
		}
		}
		}
		sc.close();
	}

}
