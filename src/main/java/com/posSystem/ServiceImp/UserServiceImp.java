package com.posSystem.ServiceImp;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.posSystem.Configuration.JwtProvider;
import com.posSystem.Exception.UserException;
import com.posSystem.Models.User;
import com.posSystem.Repositories.UserRepo;
import com.posSystem.Service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService  {
	private final UserRepo userRepo;
	private final JwtProvider jwtProvider;

	@Override
	public User getUserFromJwtToken(String token) {
		String email=jwtProvider.getEmailFromToken(token);
		User user=userRepo.findByEmail(email);
		if(user==null) {
			throw new UserException("Invalid Token !!");
		}
		return user;
	}

	@Override
	public User getCurrentUser() {
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user=userRepo.findByEmail(email);
		if(user==null) {
			throw new UserException("Invalid Token !!");
		}
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		User user=userRepo.findByEmail(email);
		if(user==null) {
			throw new UserException("Invalid Token !!");
		}
		return user;
	}

	@Override
	public User getUserById(Long id) {
		User user=userRepo.findById(id).orElseThrow(()->new UserException("Invalid Token !!"));
		
		return user;
		
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users=userRepo.findAll();
		return users;
	}

}
