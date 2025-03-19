package com.example.vti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vti.dto.request.LoginRequestDto;
import com.example.vti.dto.response.LoginResponseDto;
import com.example.vti.entities.User;
import com.example.vti.exceptions.ResourceNotFoundException;
import com.example.vti.mapper.UserMapper;
import com.example.vti.repositories.UserRepository;
import com.example.vti.security.UserPrincipal;
import com.example.vti.security.jwt.JwtUtils;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("${api.version}/users")
@Log4j2
public class AuthController {
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDto dto) {
	    try {
	        Authentication authentication = authManager.authenticate(
	            new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
	        );
	        System.out.println(authentication.getPrincipal());

	        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

	        String accessToken = jwtUtils.generateAccessToken(userDetails);
	        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
	            () -> new ResourceNotFoundException("User not found")
	        );

	        return ResponseEntity.ok(new LoginResponseDto(accessToken, UserMapper.mapToUserDto(user)));
	    } catch (BadCredentialsException ex) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout() {
	    return ResponseEntity.ok("Logged out successfully");
	}
}
