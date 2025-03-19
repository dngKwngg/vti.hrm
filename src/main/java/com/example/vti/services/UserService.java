package com.example.vti.services;

import java.util.List;

import com.example.vti.dto.UserDto;
import com.example.vti.dto.request.UserEmployeeRequestDto;
import com.example.vti.dto.request.UserRequestDto;
import com.example.vti.security.UserPrincipal;

public interface UserService {
	List<UserDto> getAllUsers();
	List<UserDto> getUsersByDepartment(Long departmentId);
	
	UserDto getUserById(Long id); 
	UserDto getUserById(Long id, Long departmentId);
	
	UserDto createUser(UserRequestDto dto);
	UserDto createUser(UserEmployeeRequestDto dto, Long departmentId);
	
	UserDto updateUser(Long id, UserRequestDto dto);
	UserDto updateUser(Long id, UserRequestDto dto, Long departmentId);
	
	void deleteUser(Long id);
	UserDto getMe(UserPrincipal userPrincipal);

	
	// Login
	
	// Logout
}
