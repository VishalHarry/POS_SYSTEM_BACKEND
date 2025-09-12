package com.posSystem.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.posSystem.Dto.UserDto;
import com.posSystem.Payload.Response.AuthResponse;
import com.posSystem.Service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	private final AuthService authService;
	
	@PostMapping("/signUp")
	public ResponseEntity<AuthResponse> signUp(@RequestBody UserDto userDto) throws Exception{
		
		return new ResponseEntity<AuthResponse>(authService.signup(userDto),HttpStatus.CREATED);
		
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody UserDto userDto) throws Exception{
		
		return new ResponseEntity<AuthResponse>(authService.login(userDto),HttpStatus.CREATED);
		
	}

}
