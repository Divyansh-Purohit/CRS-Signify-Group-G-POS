/**
 * 
 */
package com.signify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.signify.exception.UserNotFoundException;
import com.signify.jdbc.UserDAOImplementation;

/**
 * @author dp201
 *
 */

@Service
public class UserServiceOperation implements UserInterface {

	@Autowired
	private UserDAOImplementation udi;

	/**
	 * return details of login user
	 */

	public String[] login(String username, String password) {
		String[] userLoginDetails = { "", "" };
		try {
			userLoginDetails = udi.login(username, password);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return userLoginDetails;
	}

	/**
	 * logout
	 */
	public void logout() {
		System.out.println("\nYOU WILL BE LOGGED OUT!\n");
	}

	/**
	 * update details
	 */
	public void updateDetails() {
		System.out.println("\nDETAILS UPDATED!\n");
	}

	/**
	 * return update password status
	 */
	public boolean updatePassword(String username, String password, String newPassword) {
		UserDAOImplementation udi = new UserDAOImplementation();
		try {
			udi.updatePassword(username, password, newPassword);
			System.out.println("YOUR PASSWORD HAS BEEN UPDATE SUCCESSFULLY!\n");
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}
}
