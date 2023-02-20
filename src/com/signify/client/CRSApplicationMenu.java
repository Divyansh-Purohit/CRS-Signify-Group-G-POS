package com.signify.client;
import com.signify.service.*;
import com.signify.collection.*;
import com.signify.bean.*;

import java.time.LocalDate;
import java.util.*;
/**
 * @author dp201
 *
 */
public class CRSApplicationMenu {
	
	public void loginHandler()
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
					String username="DEFAULT", password="DEFAULT", role="DEFAULT";
					boolean flagInner = true;
					while(flagInner)
					{
						System.out.print("ENTER USERNAME: ");
						username = sc.next();
						System.out.print("ENTER PASSWORD: ");
						password = sc.next();
						System.out.print("ENTER ROLE: ");
						role = sc.next();
						role = role.toUpperCase();
						
						if(!role.equals("ADMIN") && !role.equals("STUDENT") && !role.equals("PROFESSOR"))
						{
							System.out.println("\nInvalid Role Entered!\n");
							continue;
						}
						else
						{
							flagInner = false;
						}
					}
					
					System.out.println("\nLogin in Process\n");
					switch(role)
					{
						case "ADMIN":
						{
							int adminId=-1;
							HashMap<Integer, Admin> hmp = UserData.admins;
							boolean flag = false;
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
							if(!flag)
							{
								System.out.println("\nInvalid username or password!\n");
								return;
							}
							CRSAdminApplicationMenu as = new CRSAdminApplicationMenu();
							as.displayAdminMenu(adminId);
							break;
						}
					
						case "PROFESSOR":
						{
							int professorId=-1;
							HashMap<Integer, Professor> hmp = UserData.professors;
							boolean flag = false;
							for(Map.Entry<Integer, Professor> m: hmp.entrySet())
							{
								Professor professor = m.getValue();
								if(username.equals(professor.getName()) && password.equals(professor.getPassword()))
								{
									professorId = professor.getUserId();
									flag = true;
									break;
								}
							}
							if(!flag)
							{
								System.out.println("\nInvalid username or password!\n");
								return;
							}
							CRSProfessorApplicationMenu ps = new CRSProfessorApplicationMenu();
							ps.displayProfessorMenu(professorId);
							break;
						}
						case "STUDENT": 
						{
							int studentId=-1;
							HashMap<Integer, Student> hmp = UserData.students;
							boolean flag = false;
							for(Map.Entry<Integer, Student> m: hmp.entrySet())
							{
								Student student = m.getValue();
								if(username.equals(student.getName()) && password.equals(student.getPassword()))
								{
									studentId = student.getUserId();
									flag = true;
									break;
								}
							}
							if(!flag)
							{
								System.out.println("\nInvalid username or password!\n");
								return;
							}
							CRSStudentApplicationMenu ss = new CRSStudentApplicationMenu();
							ss.displayStudentMenu(studentId);
							break;
						}
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
	
	public static void main(String args[])
	{
		CRSApplicationMenu cam = new CRSApplicationMenu();
		Admin admin_ = new Admin();
		admin_.setUserId(1000);
		admin_.setName("Admin_G");
		admin_.setAddress("BLR");
		admin_.setPassword("abc");
		admin_.setDoj(LocalDate.now());
		UserData.admins.put(1000, admin_);
		cam.loginHandler();
		System.out.println("\nExitting from CRS!\n");
	}
}
