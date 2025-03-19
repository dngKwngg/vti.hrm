package com.example.vti.mapper;

import com.example.vti.dto.PermissionDto;
import com.example.vti.entities.Permission;

public class PermissionMapper {
	public static Permission mapToPermission(PermissionDto permissionDto) {
		return new Permission(
				permissionDto.getPermissionId(),
				permissionDto.getPermissionName(),
				permissionDto.getDescription()
			);
	}
	
	public static PermissionDto mapToPermissionDto(Permission permission) {
		return new PermissionDto(
				permission.getPermissionId(),
				permission.getPermissionName(),
				permission.getDescription()
			);
	}
}
