package com.signify.restcontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.signify.bean.User;
import com.signify.exception.UserNotFoundException;
import com.signify.service.UserServiceOperation;

/**
 * @author HIMANSHU YADAV
 * The Class UserRestControllerAPI.
 *
 */
@RestController
public class UserRestControllerAPI {

	/** The user service. */
	@Autowired
	private UserServiceOperation userService;

	/**
	 * Login.
	 *
	 * @param usr the usr
	 * @return the response entity
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<User> login(@RequestBody User usr) {
		try {
			return new ResponseEntity<User>(userService.login(usr.getUsername(), usr.getPassword()), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<User>(new User(), HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * Update password.
	 *
	 * @param data the data
	 * @return true, if successful
	 */
	@RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
	@ResponseBody
	public boolean updatePassword(@RequestBody Map<String, String> data) {
		String username = data.get("username");
		String oldpassword = data.get("oldpassword");
		String newpassword = data.get("newpassword");
		return userService.updatePassword(username, oldpassword, newpassword);
	}
}
