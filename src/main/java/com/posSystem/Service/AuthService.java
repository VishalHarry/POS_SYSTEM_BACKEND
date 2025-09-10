package com.posSystem.Service;

import com.posSystem.Dto.UserDto;
import com.posSystem.Payload.Response.AuthResponse;

public interface AuthService {
	
	public AuthResponse signup(UserDto userDto) throws Exception;
	public AuthResponse login(UserDto userDto) throws Exception;

}
