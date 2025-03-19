package com.example.vti.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="role_permissions")
@Data
@NoArgsConstructor
public class RolePermission {
    
    @EmbeddedId
    private RolePermissionID id = new RolePermissionID();
    
    @ManyToOne
    // Maps the roleId field in RolePermissionId to Role role reference
    @MapsId("roleId")
    // Specify column role_id in Role table store foreign key
    @JoinColumn(name = "role_id")
    private Role role;
    
    @ManyToOne
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id")
    private Permission permission;
    
    public RolePermission(Role role, Permission permission) {
        this.role = role;
        this.permission = permission;
        this.id = new RolePermissionID(role.getRoleId(), permission.getPermissionId());
    }
}