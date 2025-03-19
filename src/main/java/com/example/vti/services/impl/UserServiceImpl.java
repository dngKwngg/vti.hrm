package com.example.vti.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vti.constraints.RoleType;
import com.example.vti.dto.UserDto;
import com.example.vti.dto.request.UserEmployeeRequestDto;
import com.example.vti.dto.request.UserRequestDto;
import com.example.vti.entities.User;
import com.example.vti.exceptions.ResourceNotFoundException;
import com.example.vti.mapper.UserMapper;
import com.example.vti.entities.Department;
import com.example.vti.entities.Employee;
import com.example.vti.entities.Position;
import com.example.vti.entities.Role;
import com.example.vti.repositories.UserRepository;
import com.example.vti.security.UserPrincipal;
import com.example.vti.repositories.DepartmentRepository;
import com.example.vti.repositories.EmployeeRepository;
import com.example.vti.repositories.PositionRepository;
import com.example.vti.repositories.RoleRepository;
import com.example.vti.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private PositionRepository positionRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	  
	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = userRepository.findAll();
		return users.stream()
				.map(user -> UserMapper.mapToUserDto(user))
				.toList();
	}
	
	@Override
	public List<UserDto> getUsersByDepartment(Long departmentId) {
		// TODO Auto-generated method stub
		List<User> users = userRepository.findByDepartmentId(departmentId);
		return users.stream()
				.map(user -> UserMapper.mapToUserDto(user))
				.toList();
	}

	@Override
	public UserDto getUserById(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Can't find user"));
		return UserMapper.mapToUserDto(user);
	}
	
	@Override
	public UserDto getUserById(Long id, Long departmentId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Can't find user"));
		
		if (user.getEmployee().getDepartment().getDepartmentId().equals(departmentId)) {
			return UserMapper.mapToUserDto(user);
		} else {
			throw new RuntimeException("User in other department");
		}
	}

	@Override
	public UserDto createUser(UserRequestDto dto) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setEmail(dto.getEmail());
		
		String hashedPassword = passwordEncoder.encode(dto.getPassword());
	    user.setPassword(hashedPassword);
	    
		user.setUsername(dto.getUsername());
		
		Role role = roleRepository.findById(dto.getRoleId())
				.orElseThrow(() -> new RuntimeException("ID not found"));
		user.setRole(role);
		
		User savedUser = userRepository.save(user);
		return UserMapper.mapToUserDto(savedUser);
	}
	
	@Override
	public UserDto createUser(UserEmployeeRequestDto dto, Long departmentId) {
	    // Create User entity
	    User user = new User();
	    user.setEmail(dto.getEmail());
	    
	    String hashedPassword = passwordEncoder.encode(dto.getPassword());
	    user.setPassword(hashedPassword);
	    
	    user.setUsername(dto.getUsername());

	    Role role = roleRepository.findByRoleName("EMPLOYEE")
	            .orElseThrow(() -> new ResourceNotFoundException("Can't find role"));
	    user.setRole(role);
	    
	    // Save user to database
	    User savedUser = userRepository.save(user);
	    
	    // Fetch department
	    Department department = departmentRepository.findById(departmentId)
	            .orElseThrow(() -> new ResourceNotFoundException("Can't find department"));

	    // Fetch position
	    Position position = positionRepository.findById(dto.getPositionId())
	            .orElseThrow(() -> new ResourceNotFoundException("Can't find position"));

	    // Create Employee entity and associate it with the user
	    
	    Employee employee = new Employee();
	    employee.setUser(savedUser);
	    employee.setFirstName(dto.getFirstName());
	    employee.setLastName(dto.getLastName());
	    employee.setDateOfBirth(dto.getDateOfBirth());
	    employee.setPhoneNumber(dto.getPhoneNumber());
	    employee.setAddress(dto.getAddress());
	    employee.setHireDate(LocalDate.now());
	    employee.setSalary(dto.getSalary());
	    employee.setStatus("ACTIVE");
	    employee.setDepartment(department);
	    employee.setPosition(position);

	    // Save Employee to database
	    employeeRepository.save(employee);
	    
	    return UserMapper.mapToUserDto(savedUser);
	}


	@Override
	public UserDto updateUser(Long id, UserRequestDto dto) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(
				() -> new RuntimeException("ID not found"));
		
		user.setEmail(dto.getEmail());
		user.setUsername(dto.getUsername());
		
		Role role = roleRepository.findById(dto.getRoleId())
				.orElseThrow(() -> new RuntimeException("ID not found"));
		user.setRole(role);
		
		User savedUser = userRepository.save(user);
		return UserMapper.mapToUserDto(savedUser);
	}
	
	@Override
	public UserDto updateUser(Long id, UserRequestDto dto, Long departmentId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Can't find user"));
		
		if (!user.getEmployee().getDepartment().getDepartmentId().equals(departmentId)) {
			throw new RuntimeException("User in other department");
		}
		
		user.setEmail(dto.getEmail());
		user.setUsername(dto.getUsername());
		
		Role role = roleRepository.findById(dto.getRoleId())
				.orElseThrow(() -> new RuntimeException("ID not found"));
		user.setRole(role);
		
		User savedUser = userRepository.save(user);
		return UserMapper.mapToUserDto(savedUser);
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Can't find user"));
		
		userRepository.delete(user);
		
	}

	@Override
	public UserDto getMe(UserPrincipal userPrincipal) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userPrincipal.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("Can't find user"));
		return UserMapper.mapToUserDto(user);
	}
}
