package com.example.vti.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	@NotNull (message="ID is not null")
	private Long userId;
	
	@NotBlank (message="Required username")
	private String username;
	
	@Email (message="Email is invalid")
	private String email;
	
	private Long roleId;
}
