package com.example.vti.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.vti.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
	
	@Query("SELECT u FROM User u JOIN Employee e ON u.userId = e.user.userId WHERE e.department.departmentId = :departmentId")
    List<User> findByDepartmentId(@Param("departmentId") Long departmentId);
}
