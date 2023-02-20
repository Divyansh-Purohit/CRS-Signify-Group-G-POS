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
	
	public void displayStudentMenu(int studentId)
	{
		System.out.println("\nWELCOME TO STUDENT MENU");
		System.out.println("=========================");
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("PRESS 1 TO VIEW GRADES\nPRESS 2 TO VIEW GRADE FOR A PARTICULAR COURSE\nPRESS 3 TO VIEW REGISTERED COURSE\nPRESS 4 TO ADD COURSE\nPRESS 5 TO DROP A COURSE\nPRESS 6 TO PAY FEES\nPRESS 7 TO LOGOUT\n");
			StudentInterface ss = new StudentServiceOperation();
			int choice = sc.nextInt();
			switch(choice)
			{
				case 1:
				{
					ss.viewGrades(studentId);
					break;
				}
				case 2:
				{
					System.out.print("Enter Course Code: ");
					sc.nextLine();
					String courseCode = sc.nextLine();
					ss.viewGrade(studentId, courseCode);
					break;
				}
				case 3:
				{
					ss.viewRegisterCourses(studentId);
					break;
				}
				case 4:
				{
					ss.addCourse(studentId);
					break;
				}
				case 5:
				{
					System.out.print("\nEnter Course Id: ");
					sc.nextLine();
					String courseId = sc.nextLine();
					ss.dropCourse(studentId,courseId);
					break;
				}
				case 6:
				{
					ss.payFees(studentId);
					System.out.println("\nWaiting for admin's verifcation!\n");
					break;
				}
				case 7:
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
