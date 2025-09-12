package com.posSystem.ServiceImp;

import java.time.LocalDateTime;
import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.posSystem.Configuration.JwtProvider;
import com.posSystem.Domain.UserRole;
import com.posSystem.Dto.UserDto;
import com.posSystem.Exception.UserException;
import com.posSystem.Models.User;
import com.posSystem.Payload.Response.AuthResponse;
import com.posSystem.Repositories.UserRepo;
import com.posSystem.Service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	
	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	private final CustomUserIMplementation customUserIMplementation;
	private final ModelMapper modelMapper;
	

	@Override
	public AuthResponse signup(UserDto userDto) throws Exception {
		User user=userRepo.findByEmail(userDto.getEmail());
		if(user!=null){
			throw new UserException("Email id already exist !!");
		}
		
		if(userDto.getRole().equals(UserRole.ROLE_ADMIN)) {
			throw new UserException("role admin is not allowed !!");
		}
		
		User newUser=new User();
		newUser.setEmail(userDto.getEmail());
		newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
		newUser.setRole(userDto.getRole());
		newUser.setPhone(userDto.getPhone());
		newUser.setName(userDto.getName());
		newUser.setCreateAt(LocalDateTime.now());
		newUser.setLastLoginAt(LocalDateTime.now());
		newUser.setUpdateAt(LocalDateTime.now());
		 User savedUser=userRepo.save(newUser);
		 
		 Authentication authentication=new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());
		 SecurityContextHolder.getContext().setAuthentication(authentication);
		 
		 String jwt=jwtProvider.generateToken(authentication);
		 AuthResponse authResponse=new AuthResponse();
		 authResponse.setJwt(jwt);
		 authResponse.setMessage("Register successfully !!");
		 authResponse.setUser(modelMapper.map(savedUser, UserDto.class));
		return authResponse;
	}

	@Override
	public AuthResponse login(UserDto userDto) throws Exception {
		String email=userDto.getEmail();
		String password=userDto.getPassword();
		Authentication authentication=authenticate(email,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		Collection<? extends GrantedAuthority> 	authorities=authentication.getAuthorities(); 
//		String role=authorities.iterator().next().getAuthority();
		String jwt=jwtProvider.generateToken(authentication);
		User user=userRepo.findByEmail(email);
		user.setLastLoginAt(LocalDateTime.now());
		userRepo.save(user);
		
		AuthResponse authResponse=new AuthResponse();
		 authResponse.setJwt(jwt);
		 authResponse.setMessage("Login successfully !!");
		 authResponse.setUser(modelMapper.map(user, UserDto.class));
				
		return authResponse;
	}

	private Authentication authenticate(String email, String password) {
		UserDetails userDetails=customUserIMplementation.loadUserByUsername(email);
		if(userDetails==null) {
			throw new 	UserException("email id does't exixt "+ email);
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new 	UserException("password does't exixt "+ password);
		}
		return new UsernamePasswordAuthenticationToken(userDetails, userDetails);
	}

}
