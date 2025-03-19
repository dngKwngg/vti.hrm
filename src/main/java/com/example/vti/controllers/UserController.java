package com.example.vti.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.vti.dto.UserDto;
import com.example.vti.dto.request.UserEmployeeRequestDto;
import com.example.vti.dto.request.UserRequestDto;
import com.example.vti.security.UserPrincipal;
import com.example.vti.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.version}/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('READ_USER')")
	public List<UserDto> getAllUsers(@AuthenticationPrincipal UserPrincipal userPrincipal) {
	    if (userPrincipal.isAdmin()) {
	        return userService.getAllUsers();
	    } else {
	        return userService.getUsersByDepartment(userPrincipal.getDepartmentId()); // MANAGER gets users in their department
	    }
	}
	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('READ_USER')")
	public UserDto getUserById(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long id) {
		if (userPrincipal.isAdmin()) {
			return userService.getUserById(id); 
	    } else {
	        return userService.getUserById(id, userPrincipal.getDepartmentId()); // MANAGER gets users in their department
	    }
	}
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('CREATE_USER')")
    public UserDto createUser(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody UserEmployeeRequestDto userEmployeeRequestDto) {
		if (userPrincipal.isAdmin()) {
			UserRequestDto userRequestDto = new UserRequestDto(
					userEmployeeRequestDto.getUsername(), 
					userEmployeeRequestDto.getPassword(), 
					userEmployeeRequestDto.getEmail(), 
					userEmployeeRequestDto.getRoleId()
			);
			return userService.createUser(userRequestDto); 
	    } else {
	        return userService.createUser(userEmployeeRequestDto, userPrincipal.getDepartmentId()); // MANAGER gets users in their department
	    }
    }
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('UPDATE_USER')")
	public ResponseEntity<?> updateUser(
	        @AuthenticationPrincipal UserPrincipal userPrincipal,
	        @PathVariable Long id,
	        @RequestBody @Valid UserRequestDto dto,  // ✅ Ensure validation is applied
	        BindingResult bindingResult) {  // ✅ Capture validation errors

	    // ✅ Check for validation errors BEFORE proceeding
	    if (bindingResult.hasErrors()) {
	        Map<String, String> errors = new HashMap<>();
	        for (FieldError error : bindingResult.getFieldErrors()) {
	            errors.put(error.getField(), error.getDefaultMessage());
	        }
	        return ResponseEntity.badRequest().body(errors);  // ✅ Return validation errors
	    }

	    // ✅ If validation passes, proceed with the update logic
	    if (userPrincipal.isAdmin()) {
	        return ResponseEntity.ok(userService.updateUser(id, dto));
	    } else {
	        return ResponseEntity.ok(userService.updateUser(id, dto, userPrincipal.getDepartmentId()));
	    }
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('DELETE_USER')")
	public ResponseEntity<String> deleteUserById(@PathVariable Long id){
		userService.deleteUser(id);
		return ResponseEntity.ok("User delete successfully");
	}
	
	@GetMapping("/me")
	public UserDto getMe(@AuthenticationPrincipal UserPrincipal userPrincipal) {
	    return userService.getMe(userPrincipal);
	}
}
