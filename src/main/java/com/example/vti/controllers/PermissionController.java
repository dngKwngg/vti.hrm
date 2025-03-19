package com.example.vti.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vti.dto.PermissionDto;
import com.example.vti.services.PermissionService;

@RestController
@RequestMapping("${api.version}/permissions")
public class PermissionController {
	@Autowired
	private PermissionService permissionService;
	
	@GetMapping
	public List<PermissionDto> getAllPermissions(){
		return permissionService.getAllPermissions();
	}
	
	@GetMapping("/{id}")
	public PermissionDto getPermissionById(@PathVariable Long id){
		return permissionService.getPermissionById(id);
	}
	
	@PostMapping
	public PermissionDto createPermission(@RequestBody PermissionDto permissionDto) {
		return permissionService.createPermission(permissionDto);
	}
	
	@PutMapping("/{id}")
	public PermissionDto updatePermission(@PathVariable Long id, @RequestBody PermissionDto permissionDto) {
		return permissionService.updatePermission(id, permissionDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePermission(@PathVariable Long id){
		permissionService.deletePermission(id);
		return ResponseEntity.ok("Department deleted successfully");
	}
}
