package com.example.vti.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="employee_id")
	private Long employeeId;
	
	@OneToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="last_name", nullable=false)
	private String lastName;
	
	@Column(name="date_of_birth", nullable=false)
	private LocalDate dateOfBirth;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	private String address;
	
	@Column(name="hire_date")
	private LocalDate hireDate;
	
	@Column(precision=10, scale=2)
	private BigDecimal salary;
	
	private String status;
	
	@ManyToOne
	@JoinColumn(name="position_id", nullable=false)
	private Position position;
	
	@ManyToOne
	@JoinColumn(name="department_id", nullable=false)
	private Department department;
}
