package com.example.vti.services;

import java.util.List;

import com.example.vti.dto.PermissionDto;
import com.example.vti.dto.RoleDto;
import com.example.vti.dto.RolePermissionDto;

public interface RolePermissionService {
	List<RolePermissionDto> getAllRolePermissions();
	RolePermissionDto getRolePermissionById(Long roleId, Long permissionId);
	RolePermissionDto createRolePermission(RolePermissionDto rolePermisisonDto);
	void deleteRolePermission(Long roleId, Long permissionId);
	List<PermissionDto> getPermissionsByRoleId(Long roleId);
	List<RoleDto> getRolesByPermissionId(Long permissionId);
}
