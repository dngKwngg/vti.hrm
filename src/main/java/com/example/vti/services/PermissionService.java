package com.example.vti.services;

import java.util.List;

import com.example.vti.dto.PermissionDto;

public interface PermissionService {
	List<PermissionDto> getAllPermissions();
	PermissionDto getPermissionById(Long id);
	PermissionDto createPermission(PermissionDto permissionDto);
	PermissionDto updatePermission(Long id, PermissionDto permissionDto);
	void deletePermission(Long id);
}
