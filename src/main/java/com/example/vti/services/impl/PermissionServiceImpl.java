package com.example.vti.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vti.dto.PermissionDto;
import com.example.vti.entities.Permission;
import com.example.vti.exceptions.ResourceNotFoundException;
import com.example.vti.mapper.PermissionMapper;
import com.example.vti.repositories.PermissionRepository;
import com.example.vti.services.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService{
	@Autowired
	private PermissionRepository permissionRepository;
	
	@Override
	public List<PermissionDto> getAllPermissions() {
		// TODO Auto-generated method stub
		List<Permission> permissions = permissionRepository.findAll();
		return permissions.stream()
					.map(permission -> PermissionMapper.mapToPermissionDto(permission))
					.toList();
	}

	@Override
	public PermissionDto getPermissionById(Long id) {
		// TODO Auto-generated method stub
		Permission permission = permissionRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Can't find permission"));
		return PermissionMapper.mapToPermissionDto(permission);
	}

	@Override
	public PermissionDto createPermission(PermissionDto permissionDto) {
		// TODO Auto-generated method stub
		Permission permission = PermissionMapper.mapToPermission(permissionDto);
		Permission savedPermission = permissionRepository.save(permission);
		return PermissionMapper.mapToPermissionDto(savedPermission);
	}

	@Override
	public PermissionDto updatePermission(Long id, PermissionDto permissionDto) {
		// TODO Auto-generated method stub
		Permission permission = permissionRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Can't find permission"));
		permission.setPermissionName(permissionDto.getPermissionName());
		permission.setDescription(permissionDto.getDescription());
		
		Permission savedPermission = permissionRepository.save(permission);
		return PermissionMapper.mapToPermissionDto(savedPermission);
	}

	@Override
	public void deletePermission(Long id) {
		// TODO Auto-generated method stub
		Permission permission = permissionRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Can't find permission"));
		
		permissionRepository.delete(permission);
	}

}
