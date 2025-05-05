package com.app.service;

import java.util.List;

import com.app.dtos.partdto.PartResponseDto;
import com.app.dtos.scrapyarddto.ScrapYardPartsResponseDto;
import com.app.dtos.scrapyarddto.ScrapYardRequestDto;

public interface ScrapYardService {

	List<ScrapYardPartsResponseDto> getPartByName(String partName);


	List<ScrapYardPartsResponseDto> getPartBySubcategoryId(Long partId);


	List<ScrapYardPartsResponseDto> getAllPartsBySY(Long scrapyardId);
}
