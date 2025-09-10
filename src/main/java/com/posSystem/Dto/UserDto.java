package com.posSystem.Dto;

import java.time.LocalDateTime;

import com.posSystem.Domain.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	
	private Long userId;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private String password;
	private LocalDateTime createAt;
	
	private LocalDateTime updateAt;
	
	private LocalDateTime lastLoginAt;
	
	
	@Column(nullable = false)
	private UserRole role;

}
