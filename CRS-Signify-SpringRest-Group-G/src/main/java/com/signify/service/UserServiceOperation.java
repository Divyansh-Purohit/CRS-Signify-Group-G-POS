/**
 * 
 */
package com.signify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.signify.bean.User;
import com.signify.exception.UserNotFoundException;
import com.signify.jdbc.UserDAOImplementation;

/**
 * @author dp201
 *
 */

@Service
@Primary
public class UserServiceOperation implements UserInterface {

	@Autowired
	private UserDAOImplementation udi;

	/**
	 * return details of login user
	 * 
	 * @throws UserNotFoundException
	 */
	@Override
	public User login(String username, String password) throws UserNotFoundException {
		return udi.login(username, password);
	}

	/**
	 * logout
	 */
	@Override
	public void logout() {
		System.out.println("\nYOU WILL BE LOGGED OUT!\n");
	}

	/**
	 * update details
	 */
	@Override
	public void updateDetails() {
		System.out.println("\nDETAILS UPDATED!\n");
	}

	/**
	 * return update password status
	 */
	@Override
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
