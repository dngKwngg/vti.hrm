package com.example.vti.mapper;

import com.example.vti.dto.PositionDto;
import com.example.vti.entities.Position;

public class PositionMapper {
	public static Position mapToPosition(PositionDto positionDto) {
		return new Position(
				positionDto.getPositionId(),
				positionDto.getPositionName()
			);
	}
	
	public static PositionDto mapToPositionDto(Position position) {
		return new PositionDto(
				position.getPositionId(),
				position.getPositionName()
			);
	}
}
