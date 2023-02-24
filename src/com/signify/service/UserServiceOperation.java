/**
 * 
 */
package com.signify.service;
import com.signify.exception.UserNotFoundException;
import com.signify.jdbc.UserDAOImplementation;
/**
 * @author dp201
 *
 */
public class UserServiceOperation implements UserInterface{
	
	UserDAOImplementation udi = new UserDAOImplementation();

	public String[] login(String username, String password) 
	{	
		String[] userLoginDetails = {"",""};
		try {
			userLoginDetails = udi.login(username, password);
		}
		catch(UserNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		return userLoginDetails;
	}
	
	public void logout()
	{
		System.out.println("\nYOU WILL BE LOGGED OUT!\n");
	}
		
	public void updateDetails()
	{
		System.out.println("\nDETAILS UPDATED!\n");
	}
	
	public boolean updatePassword(String username, String password, String newPassword) {
		UserDAOImplementation udi = new UserDAOImplementation();
		try {
			udi.updatePassword(username, password, newPassword);
			System.out.println("YOUR PASSWORD HAS BEEN UPDATE SUCCESSFULLY!\n");
		}
		catch(UserNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		return true;
	}
}
