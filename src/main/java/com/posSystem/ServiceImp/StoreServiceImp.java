package com.posSystem.ServiceImp;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.posSystem.Domain.StoreStatus;
import com.posSystem.Dto.StoreDto;
import com.posSystem.Exception.UserException;
import com.posSystem.Models.Store;
import com.posSystem.Models.StoreContact;
import com.posSystem.Models.User;
import com.posSystem.Repositories.StoreRepo;
import com.posSystem.Repositories.UserRepo;
import com.posSystem.Service.StoreService;
import com.posSystem.Service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImp implements StoreService{
	
	private final StoreRepo storeRepo;
	private final UserService userService;
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public StoreDto createStore(StoreDto dto, User user) {
		Store store=modelMapper.map(dto, Store.class);
		store.setStoreAdmin(user); 
		Store saved=storeRepo.save(store);
		
		return  modelMapper.map(saved, StoreDto.class) ;
	}

	@Override
	public StoreDto getStoreById(Long id) throws Exception {
		Store store=storeRepo.findById(id).orElseThrow(()-> new Exception("Not found"));
		return modelMapper.map(store, StoreDto.class);
	}

	@Override
	public List<StoreDto> getAllStore() {
		List<Store> stores=storeRepo.findAll();
		List<StoreDto> allStore=stores.stream().map(store->modelMapper.map(store, StoreDto.class)).toList();
		return allStore;
	}

	@Override
	public Store getStoreByAdmin() {
		User admin=userService.getCurrentUser();
		
		return storeRepo.findByStoreAdmin_UserId(admin.getUserId());
	}

	@Override
	public StoreDto updateStore(StoreDto dto, Long id) throws Exception {
		
		User curentUser=userService.getCurrentUser();
		Store store=storeRepo.findByStoreAdmin_UserId(curentUser.getUserId());
				if(store==null) {
					throw new Exception("Store not found");
				}
	  

	    // update fields (jo dto me aayenge)
	    store.setBrand(dto.getBrand());
	    store.setDescription(dto.getDescription());
	    if(dto.getStoreType()!=null) {
	    	store.setStoreType(dto.getStoreType());
	    }
	    if(dto.getContact()!=null) {
	    	StoreContact content=StoreContact.builder().address(dto.getContact().getAddress())
	    			                                   .email(dto.getContact().getEmail())
	    			                                   .phone(dto.getContact().getPhone())
	    			                                   .build();
	    	
	    	store.setContact(content);
	    	
	    }
	    if(dto.getStatus() != null){
	        store.setStatus(dto.getStatus());
	    }
	    store.setUpdateAt(LocalDateTime.now()); // update time set karna



//	    // save updated store
	    Store updatedStore = storeRepo.save(store);

	    // return DTO
	    return modelMapper.map(updatedStore, StoreDto.class);
	}


	@Override
	public void deleteStore(Long id) {
	
		Store store=getStoreByAdmin();
		storeRepo.delete(store);
		
		
		
		
	}

	@Override
	public StoreDto getStoreByEmployee() {
		User user=userService.getCurrentUser();
		if(user==null) {
			throw new UserException("User not found ");
		}
		Store store=user.getStore();
		  if (store == null) {
		        throw new RuntimeException("Store not found for this user");
		    }
		return modelMapper.map(store, StoreDto.class);
	}

	@Override
	public StoreDto moderateStore(Long id, StoreStatus status) throws Exception {
		Store store=storeRepo.findById(id).orElseThrow(()-> new Exception("Not found"));
		store.setStatus(status);
		Store saved=storeRepo.save(store);
		return modelMapper.map(saved, StoreDto.class);
	}
	
	
	

}
