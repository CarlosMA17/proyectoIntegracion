package com.app.service;

import java.util.List;

import com.app.dtos.partdto.PartResponseDto;
import com.app.dtos.scrapyarddto.ScrapYardPartsResponseDto;
import com.app.dtos.scrapyarddto.ScrapYardRequestDto;
import com.app.dtos.scrapyarddto.ScrapYardResponseDto;
import com.app.dtos.scrapyardpartsdto.ScrapYardPartsRequestDto;
import com.app.entity.ScrapYard;
import com.app.entity.ScrapYardParts;

public interface ScrapYardService {

	List<ScrapYardPartsResponseDto> getPartByName(String partName);


	List<ScrapYardPartsResponseDto> getPartBySubcategoryId(Long partId);


	List<ScrapYardPartsResponseDto> getAllPartsBySY(Long scrapyardId);

	void putPartToReserved(Long scrapYardPartId);


	ScrapYard findById(Long idEntity);


	void deletePart(Long scrapYardPartId);
	
	void addPart(ScrapYardPartsRequestDto scrapYardPartRequestDto);


	ScrapYardResponseDto getScrapYardByPartId(Long scrapYardPartId);
}
