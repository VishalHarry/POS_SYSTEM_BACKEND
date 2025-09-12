package com.posSystem.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.posSystem.Dto.UserDto;
import com.posSystem.Models.User;
import com.posSystem.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
	
	private final UserService userService;
	private final ModelMapper modelMapper;
	
	
	@GetMapping("/profile")
	public ResponseEntity<UserDto> getUserProfile(@RequestHeader("Authorization") String jwt){
		
		User user=userService
		.getUserFromJwtToken(jwt);
         return new ResponseEntity<UserDto>(modelMapper.map(user, UserDto.class),HttpStatus.CREATED);

	}
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@RequestHeader("Authorization") String jwt,@PathVariable Long id){
		
		User user=userService
		.getUserById(id);
         return new ResponseEntity<UserDto>(modelMapper.map(user, UserDto.class),HttpStatus.CREATED);

	}
	
	

}
