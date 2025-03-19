package com.example.vti.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.vti.dto.PositionDto;
import com.example.vti.services.PositionService;

@RestController
@RequestMapping("${api.version}/positions")
public class PositionController {
	@Autowired
	private PositionService positionService;
	
	// Get all positions REST API
	@GetMapping
	public List<PositionDto> getAllPositions(){
		return positionService.getAllPositions();
	}
	
	// Get position by id REST API
	@GetMapping("/{id}")
	public PositionDto getPositionById(@PathVariable Long id){
		return positionService.getPositionById(id);
	}
	
	// Create new position REST API
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PositionDto createPosition(@RequestBody PositionDto positionDto) {
		return positionService.createPosition(positionDto);
	}
	
	@PutMapping("/{id}")
	public PositionDto updatePostion(@PathVariable Long id,
									@RequestBody PositionDto positionDto){
		return positionService.updatePosition(id, positionDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePosition(@PathVariable Long id){
		positionService.deletePosition(id);
		return ResponseEntity.ok("Position deleted successfully");
	}
}
