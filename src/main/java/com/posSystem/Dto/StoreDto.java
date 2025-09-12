package com.posSystem.Dto;

import java.time.LocalDateTime;

import com.posSystem.Domain.StoreStatus;
import com.posSystem.Models.StoreContact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
	

	    private Long id;

	    private String brand;

	    private UserDto storeAdmin;

	    private LocalDateTime createdAt;
	    private LocalDateTime updateAt;

	    private String description;
	    private String storeType;

	   
	    private StoreStatus status;

	   
	    private StoreContact contact = new StoreContact();

	   

}
