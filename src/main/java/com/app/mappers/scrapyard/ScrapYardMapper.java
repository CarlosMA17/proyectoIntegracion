package com.app.mappers.scrapyard;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.app.entity.ScrapYard;
import com.app.entity.ScrapYardParts;
import com.app.dtos.bookdto.ScrapYardPartsResponseDto;
import com.app.dtos.bookdto.ScrapYardRequestDto;

@Mapper(componentModel = "spring")
public interface ScrapYardMapper {
	

	// Dto -> Entity
	@Mapping(target = "scrapYardId", ignore = true)
	@Mapping(target = "location", ignore = true)
	@Mapping(target = "scrapYardParts", ignore = true)
	ScrapYard toBook(ScrapYardRequestDto scrapYardRequestDto);
	
	// Entity -> Dto
	
	ScrapYardPartsResponseDto toResponse(ScrapYardParts scrapYard);
}
