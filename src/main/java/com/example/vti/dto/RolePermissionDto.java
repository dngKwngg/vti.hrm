package com.example.vti.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long roleId;
    
    private Long permissionId;
    
    private String roleName;
    private String permissionName;
}
