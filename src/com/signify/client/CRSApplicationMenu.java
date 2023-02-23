package com.signify.client;

import com.signify.service.*;
import java.util.*;

/**
 * @author dp201
 *
 */
public class CRSApplicationMenu {

	public static void main(String args[]) {

		try

		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		UserServiceOperation uso = new UserServiceOperation();
		Scanner sc = new Scanner(System.in);
		String userid = "-1", studentid = "-1";
		int roleid = -1;
		while (true) {
			System.out.println("\nWELCOME TO THE CRS APPLICATION");
			System.out.println("==============================");
			System.out.println("PRESS 1 TO LOGIN");
			System.out.println("PRESS 2 FOR STUDENT REGISTRATION");
			System.out.println("PRESS 3 TO UPDATE PASSWORD");
			System.out.println("PRESS 4 TO EXIT\n");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				System.out.print("ENTER USERNAME: ");
				String username = sc.next();
				System.out.print("ENTER PASSWORD: ");
				String password = sc.next();

				System.out.println("\nLogin in Process\n");

				String[] userLoginDetails = uso.login(username, password);

				roleid = Integer.valueOf(userLoginDetails[0]);
				userid = userLoginDetails[1];

				if (roleid == 2) {
					CRSAdminApplicationMenu as = new CRSAdminApplicationMenu();
					as.displayAdminMenu(userid);
					break;
				} else if (roleid == 3) {
					CRSProfessorApplicationMenu ps = new CRSProfessorApplicationMenu();
					ps.displayProfessorMenu(userid);
					break;
				} else if (roleid == 1) {
					StudentInterface si = new StudentServiceOperation();
					studentid = si.getStudentId(userid);
					int isApproved = si.getApprovedStatus(studentid);
					if (isApproved == 1) {
						CRSStudentApplicationMenu ss = new CRSStudentApplicationMenu();
						ss.displayStudentMenu(studentid);
					} else
						System.out.println("\nTHE ADMIN HAS NOT APPROVED YOU YET!\n");
					break;
				} else {
					System.out.println("\nINVALID USERNAME OR PASSWORD!\n");
				}
				break;
			}

			case 2: {
				StudentInterface ss = new StudentServiceOperation();
				ss.register();
				break;
			}
			case 3: {
				String username_p, password_p, newpassword_p = "DEFAULT";
				System.out.print("ENTER USERNAME: ");
				username_p = sc.next();
				System.out.print("ENTER PASSWORD: ");
				password_p = sc.next();
				UserInterface us = new UserServiceOperation();
				boolean status = us.updatePassword(username_p, password_p, newpassword_p);
				if (status) {
					System.out.println("YOUR PASSWORD HAS BEEN UPDATE UCCESSFULLY!\n");
				} else {
					System.out.println("YOUR PASSWORD COULD NOT BE UPDATED!\n");
				}
				break;
			}

			case 4: {
				uso.logout();
				return;
			}
			default: {
				System.out.println("\nINVALID INPUT RECEIVED!\n");
			}
			}
		}
	}
}
