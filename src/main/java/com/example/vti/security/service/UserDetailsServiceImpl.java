package com.example.vti.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vti.entities.Role;
import com.example.vti.entities.RolePermission;
import com.example.vti.entities.User;
import com.example.vti.repositories.RolePermissionRepository;
import com.example.vti.repositories.UserRepository;
import com.example.vti.security.UserPrincipal;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RolePermissionRepository rolePermissionRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new RuntimeException("Username not found"));
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		List<RolePermission> rolePermissions = rolePermissionRepository.findByRoleId(user.getRole().getRoleId());
		
		// From RolePermission to get Permission name
		for (RolePermission rolePermission: rolePermissions) {
			authorities.add(new SimpleGrantedAuthority(rolePermission.getPermission().getPermissionName()));
		}
		
		// Handle case where user has no department
	    Long departmentId = (user.getEmployee() != null && user.getEmployee().getDepartment() != null) 
	                            ? user.getEmployee().getDepartment().getDepartmentId() 
	                            : null;  // Assign null if department is missing
		
		return new UserPrincipal(
				user.getUserId(),
				user.getUsername(),
				user.getPassword(),
				departmentId,
				user.getRole().getRoleName(),
				authorities);
	}

}
