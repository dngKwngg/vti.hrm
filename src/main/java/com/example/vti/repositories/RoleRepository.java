package com.example.vti.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vti.constraints.RoleType;
import com.example.vti.entities.Role;
import com.example.vti.entities.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByRoleName(String roleName);
	Optional<Role> findById(Long roleId);
}