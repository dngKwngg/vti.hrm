package com.example.vti.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.vti.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	@Query("SELECT e FROM Employee e WHERE e.position.positionId = :positionId")
	List<Employee> findByPositionId(@Param("positionId") Long positionId);
	
	@Query("SELECT e FROM Employee e WHERE e.department.departmentId = :departmentId")
	List<Employee> findByDepartmentId(@Param("departmentId") Long departmentId);
}
