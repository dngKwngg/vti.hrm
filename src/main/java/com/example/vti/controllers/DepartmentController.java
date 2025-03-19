package com.example.vti.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.vti.dto.DepartmentDto;
import com.example.vti.services.DepartmentService;

@RestController
@RequestMapping("${api.version}/departments")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	
	// Get all departments REST API
	@GetMapping
	public List<DepartmentDto> getAllDepartments(){
		return departmentService.getAllDepartments();
	}
	
	// Get department by id REST API
	@GetMapping("/{id}")
	public DepartmentDto getDepartmentById(@PathVariable Long id){
		return departmentService.getDepartmentById(id);
	}
	
	// Create new department REST API
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DepartmentDto createDepartment(@RequestBody DepartmentDto departmentDto) {
		return departmentService.createDepartment(departmentDto);
	}
	
	// Update department by id REST API
	@PutMapping("/{id}")
	public DepartmentDto updateDepartment(@PathVariable Long id,
									@RequestBody DepartmentDto departmentDto) {
		return departmentService.updateDepartment(id, departmentDto);
	}
	
	// Delete department by id REST API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable Long id){
		departmentService.deleteDepartment(id);
		return ResponseEntity.ok("Department deleted successfully");
	}
}
