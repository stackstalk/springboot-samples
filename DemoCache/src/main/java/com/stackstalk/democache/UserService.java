package com.stackstalk.democache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Cacheable(value="UserDetails", key="#root.method.name")
	public List<UserDetails> getAllUsers() {
		System.out.println("Invoked getAllUsers ..");
		List<UserDetails> userList = new ArrayList<UserDetails>();
		userRepository.findAll().forEach(userList::add);
		return userList;
	}
	
	@Cacheable(value="UserDetails", key="#userId")
	public UserDetails getUser(int userId) {
		System.out.println("Invoked getUser ..");
		return userRepository.findById(userId).orElse(null);
	}	

	@CachePut(value="UserDetails", key="#userId")
	public UserDetails addUser(UserDetails user, int userId) {
		System.out.println("Invoked addUser ..");
		return userRepository.save(user);
	}
}
