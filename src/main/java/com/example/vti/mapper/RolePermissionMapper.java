package com.example.vti.mapper;

import com.example.vti.dto.RolePermissionDto;
import com.example.vti.entities.Permission;
import com.example.vti.entities.Role;
import com.example.vti.entities.RolePermission;

public class RolePermissionMapper {
	public static RolePermissionDto mapToRolePermissionDto(RolePermission rolePermission) {
		Role role = rolePermission.getRole();
		Permission permission = rolePermission.getPermission();
		
		return new RolePermissionDto(
			role.getRoleId(),
			permission.getPermissionId(),
			role.getRoleName(),
			permission.getPermissionName()
		);
	}
	
	public static RolePermission mapToRolePermission(Role role, Permission permission) {
		return new RolePermission(role, permission);
	}
}
