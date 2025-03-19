package com.example.vti.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.vti.dto.PermissionDto;
import com.example.vti.dto.RoleDto;
import com.example.vti.dto.RolePermissionDto;
import com.example.vti.entities.RolePermissionID;
import com.example.vti.services.RolePermissionService;

@RestController
@RequestMapping("${api.version}/role-permissions")
public class RolePermissionController {
	@Autowired
	private RolePermissionService rolePermissionService;
	
	@GetMapping
	public List<RolePermissionDto> getAllRolePermission(){
		return rolePermissionService.getAllRolePermissions();
	}
	
	@GetMapping("/{roleId}/{permissionId}")
	public RolePermissionDto getRolePermissionById(@PathVariable Long roleId, @PathVariable Long permissionId) {
		return rolePermissionService.getRolePermissionById(roleId, permissionId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RolePermissionDto createRolePermission(@RequestBody RolePermissionDto rolePermissionDto) {
		return rolePermissionService.createRolePermission(rolePermissionDto);
	}
	
	@DeleteMapping("/{roleId}/{permissionId}")
	public ResponseEntity<String> deleteRolePermission(@PathVariable Long roleId, @PathVariable Long permissionId){
		rolePermissionService.deleteRolePermission(roleId, permissionId);
		return ResponseEntity.ok("Role-Permission deleted successfully");
	}
	
	@GetMapping("/roles/{roleId}/permissions")
	public List<PermissionDto> getPermissionsByRoleId(@PathVariable Long roleId){
		return rolePermissionService.getPermissionsByRoleId(roleId);
	}
	
	@GetMapping("/permissions/{permissionId}/roles")
	public List<RoleDto> getRolesByPermissionId(@PathVariable Long permissionId){
		return rolePermissionService.getRolesByPermissionId(permissionId);
	}
}
