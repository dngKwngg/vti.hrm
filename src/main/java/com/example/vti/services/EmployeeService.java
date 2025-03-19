package com.example.vti.services;

import java.util.List;

import com.example.vti.dto.EmployeeDto;

public interface EmployeeService {
	List<EmployeeDto> getAllEmployees();
	EmployeeDto getEmployeeById(Long id);
	EmployeeDto createEmployee(EmployeeDto dto);
	EmployeeDto updateEmployee(Long id, EmployeeDto dto);
	void deleteEmployee(Long id);
	List<EmployeeDto> getEmployeesByDepartment(Long departmentId);
	List<EmployeeDto> getEmployeesByPosition(Long positionId);
}
