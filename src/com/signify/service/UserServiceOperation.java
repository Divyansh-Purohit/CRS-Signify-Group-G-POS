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
import com.signify.collection.UserData;
import com.signify.jdbc.StudentDAOImplementation;
import com.signify.jdbc.UserDAOImplementation;

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
					int[] details = udi.login(username,  password);
					int id = Integer.valueOf(details[1]);
					if(details[0] != -1)
					{
						
						if(details[0] == 2)
						{
							CRSAdminApplicationMenu as = new CRSAdminApplicationMenu();
							as.displayAdminMenu(id);
							break;
						}
						else if(details[0] == 3)
						{
							CRSProfessorApplicationMenu ps = new CRSProfessorApplicationMenu();
							ps.displayProfessorMenu(id);
							break;
						}
						else if(details[0] == 1)
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
					String username, password;
					UserDAOImplementation udi = new UserDAOImplementation();
					sc.nextLine();
					System.out.print("\nEnter Username: ");
					username = sc.nextLine();
					System.out.print("Enter Password: ");
					password = sc.nextLine();
					udi.updatePassword(username, password);
					break;
				}
				
				case 4:
				{
					System.out.println("\nLogging Out!\n");
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
	
	public void updatePassword() {}
}
