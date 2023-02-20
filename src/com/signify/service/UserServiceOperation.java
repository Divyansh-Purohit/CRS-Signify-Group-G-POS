/**
 * 
 */
package com.signify.service;
import java.util.*;
import com.signify.client.CRSAdminApplicationMenu;
import com.signify.client.CRSProfessorApplicationMenu;
import com.signify.client.CRSStudentApplicationMenu;

/**
 * @author dp201
 *
 */
public class UserServiceOperation implements UserInterface{
	

	public void login() {}
	
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
		System.out.println("Password Updated!");
	}
}
