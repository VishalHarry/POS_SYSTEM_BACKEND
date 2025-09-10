package com.posSystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.posSystem.Models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
  public User findByEmail(String email);
}
