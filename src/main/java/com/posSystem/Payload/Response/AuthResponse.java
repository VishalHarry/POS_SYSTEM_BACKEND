package com.posSystem.Payload.Response;

import com.posSystem.Dto.UserDto;
import com.posSystem.Models.User;

import lombok.Data;

@Data
public class AuthResponse {
	
	private String jwt;
	private String message;
	
	private UserDto user;

}
