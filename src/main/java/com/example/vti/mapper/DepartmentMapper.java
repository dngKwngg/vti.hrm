package com.example.vti.mapper;

import com.example.vti.dto.DepartmentDto;
import com.example.vti.entities.Department;

public class DepartmentMapper {
	public static Department mapToDepartment(DepartmentDto departmentDto) {
		return new Department(
				departmentDto.getDepartmentId(),
				departmentDto.getDepartmentName(),
				departmentDto.getDescription()
			);
	}
	
	public static DepartmentDto mapToDepartmentDto(Department department) {
		return new DepartmentDto(
				department.getDepartmentId(),
				department.getDepartmentName(),
				department.getDescription()
			);
	}
}
