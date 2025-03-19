package com.example.vti.mapper;

import com.example.vti.dto.EmployeeDto;
import com.example.vti.entities.Department;
import com.example.vti.entities.Employee;
import com.example.vti.entities.Position;
import com.example.vti.entities.User;

public class EmployeeMapper {
	public static EmployeeDto mapToEmployeeDto(Employee employee) {
		return new EmployeeDto(
				employee.getEmployeeId(),
				employee.getUser().getUserId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getDateOfBirth(),
				employee.getPhoneNumber(),
				employee.getAddress(),
				employee.getHireDate(),
				employee.getSalary(),
				employee.getStatus(),
				employee.getPosition().getPositionId(),
				employee.getDepartment().getDepartmentId()
			);
	}
	
	public static Employee mapToEmployee (EmployeeDto dto, User user, Position position, Department department) {
		return new Employee(
				dto.getEmployeeId(),
				user,
				dto.getFirstName(),
				dto.getLastName(),
				dto.getDateOfBirth(),
				dto.getPhoneNumber(),
				dto.getAddress(),
				dto.getHireDate(),
				dto.getSalary(),
				dto.getStatus(),
				position,
				department
			);
	}
}
