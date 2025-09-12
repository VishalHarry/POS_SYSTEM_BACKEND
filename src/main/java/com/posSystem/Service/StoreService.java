package com.posSystem.Service;

import java.util.List;

import com.posSystem.Domain.StoreStatus;
import com.posSystem.Dto.StoreDto;
import com.posSystem.Models.Store;
import com.posSystem.Models.User;

public interface StoreService {
	
	public StoreDto createStore(StoreDto dto,User user) ;
	public StoreDto getStoreById(Long id) throws Exception;
	List<StoreDto> getAllStore();
	Store getStoreByAdmin();
	StoreDto updateStore(StoreDto dto,Long id) throws Exception;
	void deleteStore(Long id);
	StoreDto getStoreByEmployee();
	
	StoreDto moderateStore(Long id,StoreStatus status) throws Exception;
	
	

}
