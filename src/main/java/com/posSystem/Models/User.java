package com.posSystem.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.posSystem.Domain.UserRole;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	private String name;
	
	private String email;
	
	private String phone;
	@OneToOne(mappedBy = "storeAdmin")
	private Store store;

	private LocalDateTime createAt;
	
	private LocalDateTime updateAt;
	
	private LocalDateTime lastLoginAt;
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private UserRole role;
	

}
