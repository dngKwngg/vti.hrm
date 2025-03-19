package com.example.vti.services;

import java.util.List;

import com.example.vti.dto.DepartmentDto;

public interface DepartmentService {
	List<DepartmentDto> getAllDepartments();
	DepartmentDto getDepartmentById(Long departmentId);
	DepartmentDto createDepartment(DepartmentDto departmentDto);
	DepartmentDto updateDepartment(Long departmentId, DepartmentDto departmentDto);
	void deleteDepartment(Long departmentId);
}
