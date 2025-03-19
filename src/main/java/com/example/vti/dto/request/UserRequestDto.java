package com.example.vti.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
	@NotBlank (message="Required username")
    private String username;
	
    private String password;
	
    @Email (message="Email is invalid")
    private String email;
    
    private Long roleId;
}
