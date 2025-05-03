package com.app.service;

import java.util.List;

import com.app.dtos.partdto.PartResponseDto;
import com.app.dtos.scrapyarddto.ScrapYardPartsResponseDto;
import com.app.dtos.scrapyarddto.ScrapYardRequestDto;

public interface ScrapYardService {

	//ScrapYardResponseDto createBook(ScrapYardRequestDto scrapYardRequestDto);
	
	List<ScrapYardPartsResponseDto> getAllParts();
	/*ScrapYardResponseDto getBookById(Long bookId);
	ScrapYardResponseDto updateBook(Long bookId, ScrapYardRequestDto scrapYardRequestDto);
	void deleteBook(Long bookId);
	
	ScrapYardResponseDto addWriterToBook(Long bookId, Long writerId);*/


	List<ScrapYardPartsResponseDto> getPartByName(String partName);


	List<ScrapYardPartsResponseDto> getPartBySubcategoryId(Long partId);
}
