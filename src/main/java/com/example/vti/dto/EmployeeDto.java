package com.example.vti.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
	private Long employeeId;
	private Long userId;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String phoneNumber;
	private String address;
	private LocalDate hireDate;
	private BigDecimal salary;
	private String status;
	private Long positionId;
	private Long DepartmentId;
}
