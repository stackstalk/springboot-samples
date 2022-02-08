package com.stackstalk.democache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(path="/users")
	public List<UserDetails> getUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping(path="/users/{userId}")
	public UserDetails getUser(@PathVariable int userId) {
		return userService.getUser(userId);
	}
	
	@PostMapping(path="/users")
	public UserDetails addUser(@RequestBody UserDetails user) {
		return userService.addUser(user, user.getUserId());
	}
}
