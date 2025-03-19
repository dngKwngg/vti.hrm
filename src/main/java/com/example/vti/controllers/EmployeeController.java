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

import com.example.vti.dto.EmployeeDto;
import com.example.vti.services.EmployeeService;

@RestController
@RequestMapping("${api.version}/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public List<EmployeeDto> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/{id}")
	public EmployeeDto getEmployeeById(@PathVariable Long id) {
		return employeeService.getEmployeeById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EmployeeDto createEmployee(@RequestBody EmployeeDto dto) {
		return employeeService.createEmployee(dto);
	}
	
	@PutMapping("/{id}")
	public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto dto) {
		return employeeService.updateEmployee(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
		employeeService.deleteEmployee(id);
		return ResponseEntity.ok("Employee delete successully");
	}
	
	@GetMapping("/department/{departmentId}")
	public List<EmployeeDto> getEmployeesByDepartment(@PathVariable Long departmentId){
		return employeeService.getEmployeesByDepartment(departmentId);
	}
	
	@GetMapping("/position/{positionId}")
	public List<EmployeeDto> getEmployeesByPosition(@PathVariable Long positionId){
		return employeeService.getEmployeesByPosition(positionId);
	}
}
