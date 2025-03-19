package com.example.vti.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vti.dto.PositionDto;
import com.example.vti.entities.Position;
import com.example.vti.exceptions.ResourceNotFoundException;
import com.example.vti.mapper.PositionMapper;
import com.example.vti.repositories.PositionRepository;
import com.example.vti.services.PositionService;

@Service
public class PositionServiceImpl implements PositionService{
	@Autowired
	private PositionRepository positionRepository;
	
	@Override
	public List<PositionDto> getAllPositions() {
		// TODO Auto-generated method stub
		List<Position> positions = positionRepository.findAll();
		return positions.stream()
			.map(position -> PositionMapper.mapToPositionDto(position))
			.toList();
	}

	@Override
	public PositionDto getPositionById(Long id) {
		// TODO Auto-generated method stub
		Position position = positionRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Can't find position"));
		return PositionMapper.mapToPositionDto(position);
	}

	@Override
	public PositionDto createPosition(PositionDto positionDto) {
		// TODO Auto-generated method stub
		Position position = PositionMapper.mapToPosition(positionDto);
		Position savedPosition = positionRepository.save(position);
		return PositionMapper.mapToPositionDto(savedPosition);
	}

	@Override
	public PositionDto updatePosition(Long id, PositionDto positionDto) {
		// TODO Auto-generated method stub
		Position position = positionRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Can't find position"));
		position.setPositionName(positionDto.getPositionName());
		Position savedPosition = positionRepository.save(position);
		return PositionMapper.mapToPositionDto(savedPosition);
	}

	@Override
	public void deletePosition(Long id) {
		// TODO Auto-generated method stub
		Position position = positionRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Can't find position"));
		
		positionRepository.delete(position);
	}

}
