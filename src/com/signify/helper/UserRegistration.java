package com.signify.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;

import com.signify.bean.User;

/**
 * @author dp201
 *
 */
public class UserRegistration {

	private String name, password, address, userid, doj;

	public User registerUser(int roleid) {

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Name: ");
		name = sc.nextLine();
		System.out.print("Enter Password: ");
		password = sc.nextLine();
		System.out.print("Enter Address: ");
		address = sc.nextLine();
		System.out.println("Enter Date of Joining (YYYY-MM-DD) or Press 1 For Today: ");
		doj = sc.nextLine();
		if(doj.equals("1"))
		{
			doj = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		userid = UUID.randomUUID().toString();

		User newUser = new User();
		newUser.setUserId(userid);
		newUser.setName(name);
		newUser.setPassword(password);
		newUser.setAddress(address);
		newUser.setDoj(doj);
		newUser.setRoleid(roleid);
		
		return newUser;
	}

}
