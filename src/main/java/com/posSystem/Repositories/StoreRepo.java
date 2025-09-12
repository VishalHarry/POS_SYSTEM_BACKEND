package com.posSystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.posSystem.Models.Store;

@Repository
public interface StoreRepo extends JpaRepository<Store, Long> {
	
	Store findByStoreAdmin_UserId(Long userId);

}
