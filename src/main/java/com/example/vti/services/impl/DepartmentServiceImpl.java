package com.example.vti.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vti.dto.DepartmentDto;
import com.example.vti.entities.Department;
import com.example.vti.exceptions.ResourceNotFoundException;
import com.example.vti.mapper.DepartmentMapper;
import com.example.vti.repositories.DepartmentRepository;
import com.example.vti.services.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public List<DepartmentDto> getAllDepartments() {
		// TODO Auto-generated method stub
		List<Department> departments = departmentRepository.findAll();
		return departments.stream()
					.map(department -> DepartmentMapper.mapToDepartmentDto(department))
					.toList();
	}

	@Override
	public DepartmentDto getDepartmentById(Long departmentId) {
		// TODO Auto-generated method stub
		Department department = departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Can't find department"));
		return DepartmentMapper.mapToDepartmentDto(department);
	}

	@Override
	public DepartmentDto createDepartment(DepartmentDto departmentDto) {
		// TODO Auto-generated method stub
		Department department = DepartmentMapper.mapToDepartment(departmentDto);
		Department savedDepartment = departmentRepository.save(department);
		return DepartmentMapper.mapToDepartmentDto(savedDepartment);
	}

	@Override
	public DepartmentDto updateDepartment(Long departmentId, DepartmentDto departmentDto) {
		// TODO Auto-generated method stub
		Department department = departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Can't find department"));
		department.setDepartmentName(departmentDto.getDepartmentName());
		department.setDescription(departmentDto.getDescription());
		
		Department savedDepartment = departmentRepository.save(department);
		return DepartmentMapper.mapToDepartmentDto(savedDepartment);
	}

	@Override
	public void deleteDepartment(Long departmentId) {
		// TODO Auto-generated method stub
		Department department = departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Can't find department"));
		
		departmentRepository.delete(department);
		
	}

}
