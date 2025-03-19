package com.example.vti.mapper;

import com.example.vti.dto.RoleDto;
import com.example.vti.entities.Role;

public class RoleMapper {
    public static Role mapToRole(RoleDto roleDto) {
        return new Role(
                roleDto.getRoleId(),
                roleDto.getRoleName(),
                roleDto.getDescription()
        );
    }

    public static RoleDto mapToRoleDto(Role role) {
        return new RoleDto(
                role.getRoleId(),
                role.getRoleName(),
                role.getDescription()
        );
    }
}