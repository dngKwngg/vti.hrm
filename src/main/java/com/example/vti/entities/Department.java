package com.example.vti.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//Lombok's annotation to generate getters, setters, equals, hashcode, and toString methods
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="departments")

public class Department {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="department_id")
	private Long departmentId;
	
	@Column(name="department_name", nullable=false)
	private String departmentName;
	
	private String description;
}
