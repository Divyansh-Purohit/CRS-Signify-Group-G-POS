/**
 * 
 */
package com.signify.service;
import java.util.*;

import com.signify.bean.Admin;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.client.CRSAdminApplicationMenu;
import com.signify.client.CRSProfessorApplicationMenu;
import com.signify.client.CRSStudentApplicationMenu;
import com.signify.collection.UserDAOImplementation;
import com.signify.collection.UserData;
import com.signify.jdbc.StudentDAOImplementation;

/**
 * @author dp201
 *
 */
public class UserServiceOperation implements UserInterface{
	

	public void login() 
	{
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("\nWELCOME TO THE CRS APPLICATION");
			System.out.println("==============================");
			System.out.println("PRESS 1 TO LOGIN");
			System.out.println("PRESS 2 FOR STUDENT REGISTRATION");
			System.out.println("PRESS 3 TO UPDATE PASSWORD");
			System.out.println("PRESS 4 TO EXIT\n");
			int choice = sc.nextInt();
			switch(choice)
			{
				case 1:
				{
					String username="DEFAULT", password="DEFAULT";
					System.out.print("ENTER USERNAME: ");
					username = sc.next();
					System.out.print("ENTER PASSWORD: ");
					password = sc.next();
					System.out.println("\nLogin in Process\n");
				
					UserDAOImplementation udi = new UserDAOImplementation();
					String[] details = udi.login(username,  password);
					int id = Integer.valueOf(details[1]);
					
					if(!(details[0] == null))
					{
						
						if(details[0].equals("admin"))
						{
							CRSAdminApplicationMenu as = new CRSAdminApplicationMenu();
							as.displayAdminMenu(id);
							break;
						}
						else if(details[0].equals("professor"))
						{
							CRSProfessorApplicationMenu ps = new CRSProfessorApplicationMenu();
							ps.displayProfessorMenu(id);
							break;
						}
						else if(details[0].equals("student"))
						{
		
							StudentDAOImplementation sdi = new StudentDAOImplementation();
							boolean isApproved = sdi.getIsApprovedStatus(id);
							CRSStudentApplicationMenu ss = new CRSStudentApplicationMenu();
							if(isApproved)
								ss.displayStudentMenu(id);
							else
								System.out.println("\nYou aren't approved yet!\n");
							break;
						}
					}
					else
					{
						System.out.println("\nInvalid username or password!\n");
					}
					break;
				}
			
				case 2:
				{
					StudentInterface ss = new StudentServiceOperation();
					ss.register();
					break;			
				}
				case 3:
				{
					UserInterface us = new UserServiceOperation();
					us.updatePassword();
					break;
				}
				
				case 4:
				{
					return;
				}
				default:
				{
					System.out.println("\nInvalid Input Received\n");
				}
			}
		}
	}
	
	public void logout()
	{
		System.out.println("Logout Successful");
	}
	
	
	public void updateDetails()
	{
		System.out.println("Details Updated!");
	}
	
	
	public void updatePassword()
	{
		String username, password, role, newPassword;
		int studentId = -1, professorId = -1, adminId = -1;
		Scanner sc = new Scanner(System.in);
//		sc.nextLine();
//		while(true) 
//		{
			System.out.print("Enter username: ");
			username = sc.nextLine();
			System.out.print("Enter password: ");
			password = sc.nextLine();
			System.out.print("Enter role: ");
			role = sc.nextLine().toUpperCase();
			boolean flag = false;
			switch(role)
			{
				case "STUDENT":
				{
					HashMap<Integer, Student> hmp = UserData.students;
					for(Map.Entry<Integer, Student> m: hmp.entrySet())
					{
						Student student = m.getValue();
						if(username.equals(student.getName()) && password.equals(student.getPassword()))
						{
							studentId = student.getStudentId();
							flag = true;
							break;
						}
					}
							
					if(!flag) {
						System.out.println("\nUsername and Password did not match!\n");
						return;
					}
					
					System.out.print("Enter new password: ");
					newPassword = sc.nextLine();
					try {
						UserData.students.get(studentId).setPassword(newPassword);
					}
					catch(Exception e)
					{
						System.out.println("\nPassword could not be updated, please try again!");
						return;
					}
					System.out.println("\nPassword Updated Successfully!\n");
					break;
				}
				case "PROFESSOR":
				{
					HashMap<Integer, Professor> hmp = UserData.professors;
					for(Map.Entry<Integer, Professor> m: hmp.entrySet())
					{
						Professor prf = m.getValue();
						if(username.equals(prf.getName()) && password.equals(prf.getPassword()))
						{
							professorId = prf.getUserId();
							flag = true;
							break;
						}
					}
					if(!flag) {
						System.out.println("\nUsername and Password did not match!\n");
						return;
					}
					
					System.out.print("Enter new password: ");
					newPassword = sc.nextLine();
					try {
						UserData.professors.get(professorId).setPassword(newPassword);
					}
					catch(Exception e)
					{
						System.out.println("\nPassword could not be updated, please try again!");
						return;
					}
					System.out.println("\nPassword Updated Successfully!\n");
					break;
				}
				case "ADMIN":
				{
					HashMap<Integer, Admin> hmp = UserData.admins;
					for(Map.Entry<Integer, Admin> m: hmp.entrySet())
					{
						Admin admin = m.getValue();
						if(username.equals(admin.getName()) && password.equals(admin.getPassword()))
						{
							adminId = admin.getUserId();
							flag = true;
							break;
						}
					}
					if(!flag) {
						System.out.println("\nUsername and Password did not match!\n");
						return;
					}
					
					System.out.print("Enter new password: ");
					newPassword = sc.nextLine();
					try {
						UserData.admins.get(adminId).setPassword(newPassword);
					}
					catch(Exception e)
					{
						System.out.println("\nPassword could not be updated, please try again!");
						return;
					}
					System.out.println("\nPassword Updated Successfully!\n");
					break;
				}
				default:
				{
					System.out.println("\nInvalid Input Received!\n");
				}
			}
//		}
	}
}
