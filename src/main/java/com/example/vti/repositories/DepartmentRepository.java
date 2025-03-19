package com.example.vti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vti.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
}
