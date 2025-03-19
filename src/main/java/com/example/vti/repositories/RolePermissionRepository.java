package com.example.vti.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.vti.entities.RolePermission;
import com.example.vti.entities.RolePermissionID;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionID>{
	@Query("SELECT rp FROM RolePermission rp WHERE rp.id.roleId = :roleId")
    List<RolePermission> findByRoleId(@Param("roleId") Long roleId);
	
	@Query("SELECT rp FROM RolePermission rp WHERE rp.id.permissionId = :permissionId")
	List<RolePermission> findByPermissionId(@Param("permissionId") Long permissionId);
}
