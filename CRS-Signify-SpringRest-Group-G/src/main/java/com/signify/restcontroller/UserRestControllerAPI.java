package com.signify.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.signify.bean.User;
import com.signify.service.UserServiceOperation;

@RestController
public class UserRestControllerAPI {

	private UserServiceOperation userService = new UserServiceOperation();

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String[] login(@RequestBody User usr) {
		return userService.login(usr.getUsername(), usr.getPassword());
	}

	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public boolean updatePassword(@RequestBody String username, String oldPassword, String newPassword) {
		return userService.updatePassword(username, oldPassword, newPassword);
	}
}
