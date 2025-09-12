package com.posSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.posSystem.Configuration.JwtProvider;
import com.posSystem.Domain.StoreStatus;
import com.posSystem.Dto.StoreDto;
import com.posSystem.Models.Store;
import com.posSystem.Models.User;
import com.posSystem.Payload.Response.ApiResponse;
import com.posSystem.Service.StoreService;
import com.posSystem.Service.UserService;

@RestController
@RequestMapping("/api/store")
public class StoreController {
	@Autowired
	private StoreService storeService;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private UserService userService;
	
	@PostMapping("/created")
	public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto dto,@RequestHeader("Authorization") String jwt){
		
		
		User user=userService.getUserFromJwtToken(jwt);
		StoreDto resp=storeService.createStore(dto, user);
		
		return new ResponseEntity<StoreDto>(resp,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StoreDto> getStoreById(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws Exception{
		
		
	
		StoreDto resp=storeService.getStoreById(id);
		
		return new ResponseEntity<StoreDto>(resp,HttpStatus.OK);
		
	}
	
	@PutMapping("/status/{id}")
	public ResponseEntity<StoreDto> moderateStatus(@PathVariable Long id,@RequestParam StoreStatus status,@RequestHeader("Authorization") String jwt) throws Exception{
		
		
	
		StoreDto resp=storeService.moderateStore(id, status);
		
		return new ResponseEntity<StoreDto>(resp,HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<StoreDto>> getAllStore(@RequestHeader("Authorization") String jwt) throws Exception{
		
		
	
		List<StoreDto> resp=storeService.getAllStore();
		
		return new ResponseEntity<List<StoreDto>>(resp,HttpStatus.OK);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<StoreDto> updateStore(@RequestBody StoreDto dto,@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws Exception{
		
		
		
		StoreDto resp=storeService.updateStore(dto, id);
		
		return new ResponseEntity<StoreDto>(resp,HttpStatus.OK);
		
	}
	
	@GetMapping("/admin")
	public ResponseEntity<Store> getStoreByAdmin(@RequestHeader("Authorization") String jwt) throws Exception{
		
		
	
		Store resp=storeService.getStoreByAdmin();
		
		return new ResponseEntity<Store>(resp,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws Exception{
		
		
          storeService.deleteStore(id);	
          
          ApiResponse resp=new ApiResponse();
          resp.setMessage("Delete Store Sucessfully !!");
		return new ResponseEntity<ApiResponse>(resp,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/employee")
	public ResponseEntity<StoreDto> getStoreByEmployee(@RequestHeader("Authorization") String jwt) throws Exception{
		
		
	
		StoreDto resp=storeService.getStoreByEmployee();
		
		return new ResponseEntity<StoreDto>(resp,HttpStatus.OK);
		
	}
	
	
	

}
