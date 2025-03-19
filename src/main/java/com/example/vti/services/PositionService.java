package com.example.vti.services;

import java.util.List;

import com.example.vti.dto.PositionDto;

public interface PositionService {
	List<PositionDto> getAllPositions();
	PositionDto getPositionById(Long id);
	PositionDto createPosition(PositionDto positionDto);
	PositionDto updatePosition(Long id, PositionDto positionDto);
	void deletePosition(Long id);
}
