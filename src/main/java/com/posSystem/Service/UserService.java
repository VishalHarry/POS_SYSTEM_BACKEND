package com.posSystem.Service;

import java.util.List;

import com.posSystem.Models.User;

public interface UserService {
	
	public User getUserFromJwtToken(String token);
	
	public User getCurrentUser();
	public User getUserByEmail(String email);
	public User getUserById(Long id);
	public List<User> getAllUsers();
	

}
