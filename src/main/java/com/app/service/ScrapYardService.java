package com.app.service;

import java.util.List;

import com.app.dtos.partdto.PartResponseDto;
import com.app.dtos.scrapyarddto.ScrapYardPartsResponseDto;
import com.app.dtos.scrapyarddto.ScrapYardRequestDto;
import com.app.entity.PartId;
import com.app.entity.ScrapYard;

public interface ScrapYardService {

	List<ScrapYardPartsResponseDto> getPartByName(String partName);


	List<ScrapYardPartsResponseDto> getPartBySubcategoryId(Long partId);


	List<ScrapYardPartsResponseDto> getAllPartsBySY(Long scrapyardId);

	void putPartToReserved(PartId idEntity);


	ScrapYard findById(Long idEntity);
}
