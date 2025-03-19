package com.example.vti.mapper;

import com.example.vti.dto.UserDto;
import com.example.vti.entities.Role;
import com.example.vti.entities.User;

public class UserMapper {

	public static UserDto mapToUserDto(User user) {
		return new UserDto(
				user.getUserId(),
				user.getUsername(),
				user.getEmail(),
				user.getRole().getRoleId()
			);
	}
	
	public static User mapToUser(UserDto userDto, Role role) {
		User user = new User();
		
		user.setUserId(userDto.getUserId());
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		
		user.setRole(role);
		return user;
	}
}
