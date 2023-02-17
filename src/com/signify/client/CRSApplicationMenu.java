/**
 * 
 */
package com.signify.client;

import java.util.Scanner;
import com.signify.client.*;
import com.signify.service.*;

/**
 * @author dp201
 *
 */
public class CRSApplicationMenu {
	
	public void loginHandler()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("WELCOME TO THE CRS APPLICATION");
		System.out.println("==============================");
		System.out.println("PRESS 1 TO LOGIN");
		System.out.println("PRESS 2 FOR STUDENT REGISTRATION");
		System.out.println("PRESS 3 TO UPDATE PASSWORD");
		System.out.println("PRESS 4 TO EXIT");
		int choice;
		do {
		choice = sc.nextInt();
		switch(choice)
		{
		case 1:
		{
			System.out.print("ENTER USERNAME: ");
			String username = sc.next();
			System.out.print("ENTER PASSWORD: ");
			String password = sc.next();
			System.out.print("ENTER ROLE: ");
			String role = sc.next();
			role = role.toUpperCase();
			do {
			
			if(!role.equals("ADMIN") && !role.equals("STUDENT") && !role.equals("PROFESSOR"))
			{
				System.out.println("Invalid Role Entered.\n");
				System.out.print("ENTER USERNAME: ");
				username = sc.next();
				System.out.print("ENTER PASSWORD: ");
				password = sc.next();
				System.out.print("ENTER ROLE: ");
				role = sc.next();
				role = role.toUpperCase();
				continue;
			}
			switch(role)
			{
			case "ADMIN":
			{
				CRSAdminApplicationMenu as = new CRSAdminApplicationMenu();
				as.displayAdminMenu();
				break;
			}
			
			case "PROFESSOR":
			{
				CRSProfessorApplicationMenu ps = new CRSProfessorApplicationMenu();
				ps.displayProfessorMenu();
				break;
			}
			case "STUDENT": 
			{
				CRSStudentApplicationMenu ss = new CRSStudentApplicationMenu();
				ss.displayStudentMenu();
				break;
			}
			}
			
			break;
		}while(!role.equals("ADMIN") && !role.equals("STUDENT") && !role.equals("PROFESSOR"));
		}
		
		case 2:
		{
			StudentServiceOperation ss = new StudentServiceOperation();
			ss.register();
			break;			
		}
		case 3:
		{
			UserServiceOperation us = new UserServiceOperation();
			us.updatePassword();
		}
		
		case 4:
		{
			System.out.println("EXITTING FROM CRS!");
			break;
		}
		}
		}while(choice!=4);
		sc.close();
	}
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("WELCOME TO THE CRS APPLICATION");
		System.out.println("==============================");
		System.out.println("PRESS 1 TO LOGIN");
		System.out.println("PRESS 2 FOR STUDENT REGISTRATION");
		System.out.println("PRESS 3 TO UPDATE PASSWORD");
		System.out.println("PRESS 4 TO EXIT");
		int choice = sc.nextInt();
		switch(choice)
		{
		case 1:
		{
			System.out.print("ENTER USERNAME: ");
			String username = sc.next();
			System.out.print("ENTER PASSWORD: ");
			String password = sc.next();
			System.out.print("ENTER ROLE: ");
			String role = sc.next();
			role = role.toUpperCase();
			System.out.println(role);
			if(!role.equals("ADMIN") && !role.equals("STUDENT") && !role.equals("PROFESSOR"))
			{
				System.out.println("Invalid Role Entered!");
				CRSApplicationMenu crsam = new CRSApplicationMenu();
				crsam.loginHandler();
			}
			switch(role)
			{
			case "ADMIN":
			{
				CRSAdminApplicationMenu as = new CRSAdminApplicationMenu();
				as.displayAdminMenu();
				break;
			}
			
			case "PROFESSOR":
			{
				CRSProfessorApplicationMenu ps = new CRSProfessorApplicationMenu();
				ps.displayProfessorMenu();
				break;
			}
			case "STUDENT": 
			{
				CRSStudentApplicationMenu ss = new CRSStudentApplicationMenu();
				ss.displayStudentMenu();
				break;
			}
			}
			
			break;
		}
		
		case 2:
		{
			StudentServiceOperation ss = new StudentServiceOperation();
			ss.register();
			break;			
		}
		case 3:
		{
			UserServiceOperation us = new UserServiceOperation();
			us.updatePassword();
		}
		
		case 4:
		{
			System.out.println("EXITTING FROM CRS!");
			System.exit(1);
			break;
		}
		default:
		{
			System.out.println("Invalid Choice Entered.");
			CRSApplicationMenu crsam = new CRSApplicationMenu();
			crsam.loginHandler();

			
		}
		}
	}
}
