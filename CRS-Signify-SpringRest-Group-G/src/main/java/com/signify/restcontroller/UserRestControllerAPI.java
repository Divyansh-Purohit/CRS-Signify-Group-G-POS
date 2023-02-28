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

@RestController
public class UserRestControllerAPI {

	@Autowired
	private UserServiceOperation userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<User> login(@RequestBody User usr) {
		try {
			return new ResponseEntity<User>(userService.login(usr.getUsername(), usr.getPassword()), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<User>(new User(), HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
	@ResponseBody
	public boolean updatePassword(@RequestBody Map<String, String> data) {
		String username = data.get("username");
		String oldpassword = data.get("oldpassword");
		String newpassword = data.get("newpassword");
		return userService.updatePassword(username, oldpassword, newpassword);
	}
}
