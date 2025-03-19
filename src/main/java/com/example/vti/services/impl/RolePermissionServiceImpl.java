package com.example.vti.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vti.dto.PermissionDto;
import com.example.vti.dto.RoleDto;
import com.example.vti.dto.RolePermissionDto;
import com.example.vti.entities.Permission;
import com.example.vti.entities.Role;
import com.example.vti.entities.RolePermission;
import com.example.vti.entities.RolePermissionID;
import com.example.vti.exceptions.ResourceNotFoundException;
import com.example.vti.mapper.PermissionMapper;
import com.example.vti.mapper.RoleMapper;
import com.example.vti.mapper.RolePermissionMapper;
import com.example.vti.repositories.PermissionRepository;
import com.example.vti.repositories.RolePermissionRepository;
import com.example.vti.repositories.RoleRepository;
import com.example.vti.services.RolePermissionService;

@Service
public class RolePermissionServiceImpl implements RolePermissionService{
	@Autowired
	private RolePermissionRepository rolePermissionRepository;
	
	@Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PermissionRepository permissionRepository;
	
	@Override
	public List<RolePermissionDto> getAllRolePermissions() {
		// TODO Auto-generated method stub
		List<RolePermission> rolePermissions = rolePermissionRepository.findAll();
		
		return rolePermissions.stream()
					.map(rolePermission -> RolePermissionMapper.mapToRolePermissionDto(rolePermission))
					.toList();
	}

	@Override
	public RolePermissionDto getRolePermissionById(Long roleId, Long permissionId) {
		// TODO Auto-generated method stub
		RolePermissionID rolePermissionId = new RolePermissionID(roleId, permissionId);
		RolePermission rolePermission = rolePermissionRepository.findById(rolePermissionId).orElseThrow(
				() -> new ResourceNotFoundException("Can't find role-permission"));
		return RolePermissionMapper.mapToRolePermissionDto(rolePermission);
	}

	@Override
	public RolePermissionDto createRolePermission(RolePermissionDto rolePermisisonDto) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findById(rolePermisisonDto.getRoleId()).orElseThrow(
				() -> new ResourceNotFoundException("Can't find role"));
		
		Permission permission = permissionRepository.findById(rolePermisisonDto.getPermissionId()).orElseThrow(
				() -> new ResourceNotFoundException("Can't find permission"));
		
		RolePermission rolePermission = RolePermissionMapper.mapToRolePermission(role, permission);
		RolePermission savedRolePermission = rolePermissionRepository.save(rolePermission);
		
		return RolePermissionMapper.mapToRolePermissionDto(savedRolePermission);
	}

	@Override
	public void deleteRolePermission(Long roleId, Long permissionId) {
		// TODO Auto-generated method stub
		RolePermissionID rolePermissionId = new RolePermissionID(roleId, permissionId);
		RolePermission rolePermission = rolePermissionRepository.findById(rolePermissionId).orElseThrow(
				() -> new ResourceNotFoundException("Can't find role-permission"));
		
		rolePermissionRepository.delete(rolePermission);
	}

	@Override
	public List<PermissionDto> getPermissionsByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		List<RolePermission> rolePermissions = rolePermissionRepository.findByRoleId(roleId);
		List<PermissionDto> permissions = rolePermissions.stream()
			.map(rolePermission -> PermissionMapper.mapToPermissionDto(rolePermission.getPermission()))
			.toList();
		
		return permissions;
	}

	@Override
	public List<RoleDto> getRolesByPermissionId(Long permissionId) {
		// TODO Auto-generated method stub
		List<RolePermission> rolePermissions = rolePermissionRepository.findByPermissionId(permissionId);
		List<RoleDto> roles = rolePermissions.stream()
				.map(rolePermission -> RoleMapper.mapToRoleDto(rolePermission.getRole()))
				.toList();
		
		return roles;
	}

}
