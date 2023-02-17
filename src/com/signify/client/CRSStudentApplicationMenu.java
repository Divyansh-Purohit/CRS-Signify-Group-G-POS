/**
 * 
 */
package com.signify.client;
import com.signify.service.StudentInterface;
import java.util.*;
import com.signify.service.*;
/**
 * @author dp201
 *
 */
public class CRSStudentApplicationMenu {
	public static void main(String[] args) {

	}
	
	public void displayStudentMenu()
	{
		System.out.println("WELCOME TO STUDENT MENU");
		System.out.println("=========================");
		Scanner sc = new Scanner(System.in);
		System.out.println("PRESS 1 TO VIEW GRADES\nPRESS 2 TO VIEW GRADE FOR A PARTICULAR COURSE\nPRESS 3 TO VIEW REGISTERED COURSE\nPRESS 4 TO ADD COURSE\nPRESS 5 TO DROP A COURSE\nPRESS 6 TO PAY FEES\nPRESS 7 TO GO BACK");
//		StudentServiceOperation ss = new StudentServiceOperation();
		StudentInterface ss = new StudentServiceOperation();
		int choice = sc.nextInt();
		while(choice<=7)
		{
		switch(choice)
		{
		case 1:
		{
			ss.viewGrades();
			System.out.println("PRESS 1 TO VIEW GRADES\nPRESS 2 TO VIEW GRADE FOR A PARTICULAR COURSE\nPRESS 3 TO VIEW REGISTERED COURSE\nPRESS 4 TO ADD COURSE\nPRESS 5 TO DROP A COURSE\nPRESS 6 TO PAY FEES\nPRESS 7 TO GO BACK");
			choice = sc.nextInt();
			break;
		}
		case 2:
		{
			ss.viewGrade(0);
			System.out.println("PRESS 1 TO VIEW GRADES\nPRESS 2 TO VIEW GRADE FOR A PARTICULAR COURSE\nPRESS 3 TO VIEW REGISTERED COURSE\nPRESS 4 TO ADD COURSE\nPRESS 5 TO DROP A COURSE\nPRESS 6 TO PAY FEES\nPRESS 7 TO GO BACK");
			choice = sc.nextInt();
			break;
		}
		case 3:
		{
			ss.viewRegisterCourses();
			System.out.println("PRESS 1 TO VIEW GRADES\nPRESS 2 TO VIEW GRADE FOR A PARTICULAR COURSE\nPRESS 3 TO VIEW REGISTERED COURSE\nPRESS 4 TO ADD COURSE\nPRESS 5 TO DROP A COURSE\nPRESS 6 TO PAY FEES\nPRESS 7 TO GO BACK");
			choice = sc.nextInt();
			break;
		}
		case 4:
		{
			ss.addCourse();
			System.out.println("PRESS 1 TO VIEW GRADES\nPRESS 2 TO VIEW GRADE FOR A PARTICULAR COURSE\nPRESS 3 TO VIEW REGISTERED COURSE\nPRESS 4 TO ADD COURSE\nPRESS 5 TO DROP A COURSE\nPRESS 6 TO PAY FEES\nPRESS 7 TO GO BACK");
			choice = sc.nextInt();
			break;
		}
		case 5:
		{
			ss.dropCourse(0);
			System.out.println("PRESS 1 TO VIEW GRADES\nPRESS 2 TO VIEW GRADE FOR A PARTICULAR COURSE\nPRESS 3 TO VIEW REGISTERED COURSE\nPRESS 4 TO ADD COURSE\nPRESS 5 TO DROP A COURSE\nPRESS 6 TO PAY FEES\nPRESS 7 TO GO BACK");
			choice = sc.nextInt();
			break;
		}
		case 6:
		{
			ss.payFees();
			System.out.println("PRESS 1 TO VIEW GRADES\nPRESS 2 TO VIEW GRADE FOR A PARTICULAR COURSE\nPRESS 3 TO VIEW REGISTERED COURSE\nPRESS 4 TO ADD COURSE\nPRESS 5 TO DROP A COURSE\nPRESS 6 TO PAY FEES\nPRESS 7 TO GO BACK");
			choice = sc.nextInt();
			break;
		}
		case 7:
		{
			CRSApplicationMenu crss = new CRSApplicationMenu();
			crss.loginHandler();
			break;
		}
		}
		}
	}

}
