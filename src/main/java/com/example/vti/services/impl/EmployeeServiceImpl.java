package com.example.vti.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vti.dto.EmployeeDto;
import com.example.vti.entities.Employee;
import com.example.vti.entities.User;
import com.example.vti.exceptions.ResourceNotFoundException;
import com.example.vti.entities.Position;
import com.example.vti.entities.Department;
import com.example.vti.mapper.EmployeeMapper;
import com.example.vti.repositories.DepartmentRepository;
import com.example.vti.repositories.EmployeeRepository;
import com.example.vti.repositories.PositionRepository;
import com.example.vti.repositories.UserRepository;
import com.example.vti.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PositionRepository positionRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public List<EmployeeDto> getAllEmployees() {
		// TODO Auto-generated method stub
		List<Employee> employees = employeeRepository.findAll();
		
		return employees.stream()
					.map(employee -> EmployeeMapper.mapToEmployeeDto(employee))
					.toList();
	}

	@Override
	public EmployeeDto getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		Employee employee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Can't find employee"));
		
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public EmployeeDto createEmployee(EmployeeDto dto) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(dto.getUserId()).orElseThrow(
				() -> new ResourceNotFoundException("Can't find user"));
		
		Position position = positionRepository.findById(dto.getPositionId()).orElseThrow(
				() -> new ResourceNotFoundException("Can't find position"));
		
		Department department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow(
				() -> new ResourceNotFoundException("Can't find department"));
		
		Employee employee = EmployeeMapper.mapToEmployee(dto, user, position, department);
		Employee savedEmployee = employeeRepository.save(employee);
		
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
		// TODO Auto-generated method stub
		Employee employee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Can't find employee"));
		
		User user = userRepository.findById(dto.getUserId()).orElseThrow(
				() -> new ResourceNotFoundException("Can't find user"));
		
		Position position = positionRepository.findById(dto.getPositionId()).orElseThrow(
				() -> new ResourceNotFoundException("Can't find position"));
		
		Department department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow(
				() -> new ResourceNotFoundException("Can't find department"));
		
		employee.setUser(user);
		employee.setFirstName(dto.getFirstName());
		employee.setLastName(dto.getLastName());
		employee.setDateOfBirth(dto.getDateOfBirth());
		employee.setPhoneNumber(dto.getPhoneNumber());
		employee.setAddress(dto.getAddress());
		employee.setHireDate(dto.getHireDate());
		employee.setSalary(dto.getSalary());
		employee.setStatus(dto.getStatus());
		employee.setPosition(position);
		employee.setDepartment(department);
		
		Employee updatedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
	}

	@Override
	public void deleteEmployee(Long id) {
		// TODO Auto-generated method stub
		Employee employee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Can't find employee"));
		
		employeeRepository.delete(employee);
	}

	@Override
	public List<EmployeeDto> getEmployeesByDepartment(Long departmentId) {
		// TODO Auto-generated method stub
		List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
		return employees.stream()
				.map(employee -> EmployeeMapper.mapToEmployeeDto(employee))
				.toList();
	}

	@Override
	public List<EmployeeDto> getEmployeesByPosition(Long positionId) {
		// TODO Auto-generated method stub
		List<Employee> employees = employeeRepository.findByPositionId(positionId);
		return employees.stream()
				.map(employee -> EmployeeMapper.mapToEmployeeDto(employee))
				.toList();
	}

}
