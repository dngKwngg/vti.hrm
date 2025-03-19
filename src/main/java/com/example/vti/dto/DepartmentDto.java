package com.example.vti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//Lombok's annotation to generate getters, setters, equals, hashcode, and toString methods
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
	private Long departmentId;
	private String departmentName;
	private String description;
	
}
