package com.example.vti.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vti.constraints.RoleType;
import com.example.vti.dto.RoleDto;
import com.example.vti.entities.Role;
import com.example.vti.exceptions.ResourceNotFoundException;
import com.example.vti.mapper.RoleMapper;
import com.example.vti.repositories.RoleRepository;
import com.example.vti.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    
    private static final List<RoleType> ALLOWED_ROLES = Arrays.asList(RoleType.ADMIN, RoleType.MANAGER, RoleType.EMPLOYEE);

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(RoleMapper::mapToRoleDto)
                .toList();
    }

    @Override
    public RoleDto getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Can't find role"));
        return RoleMapper.mapToRoleDto(role);
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        // First check if the role already exists
        if (roleRepository.findByRoleName(roleDto.getRoleName()).isPresent()) {
            throw new IllegalArgumentException("Role already exists: " + roleDto.getRoleName());
        }

        // Then validate the role name against allowed values
        boolean isValidRole = false;
        try {
            // Try to match the role name with any of the enum values
            for (RoleType type : RoleType.values()) {
                if (type.getValue().equalsIgnoreCase(roleDto.getRoleName())) {
                    isValidRole = true;
                    break;
                }
            }
            
            if (!isValidRole) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            // Create a list of allowed role values
            List<String> allowedRoles = Arrays.stream(RoleType.values())
                    .map(RoleType::getValue)
                    .collect(Collectors.toList());
                    
            throw new IllegalArgumentException("Invalid role! Allowed roles: " + allowedRoles);
        }

        // If we reach here, the role is valid and doesn't exist yet
        Role role = RoleMapper.mapToRole(roleDto);
        Role savedRole = roleRepository.save(role);
        return RoleMapper.mapToRoleDto(savedRole);
    }


    @Override
    public RoleDto updateRole(Long id, RoleDto roleDto) {
    	Role role = roleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Can't find role"));
        role.setRoleName(roleDto.getRoleName());
        role.setDescription(roleDto.getDescription());
        
        Role savedRole = roleRepository.save(role);
        return RoleMapper.mapToRoleDto(savedRole);
    }

    @Override
    public void deleteRole(Long id) {
    	Role role = roleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Can't find role"));
        roleRepository.delete(role);
    }
}