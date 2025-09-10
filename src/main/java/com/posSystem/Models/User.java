package com.posSystem.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.posSystem.Domain.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
	@Id
	private Long userId;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private LocalDateTime createAt;
	
	private LocalDateTime updateAt;
	
	private LocalDateTime lastLoginAt;
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private UserRole role;
	

}
