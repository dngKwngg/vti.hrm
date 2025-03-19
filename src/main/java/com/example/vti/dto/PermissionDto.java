package com.example.vti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//Lombok's annotation to generate getters, setters, equals, hashcode, and toString methods
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto {
	private Long permissionId;
	private String permissionName;
	private String description;
}
