package com.app.service;

import java.util.List;

import com.app.dtos.bookdto.ScrapYardPartsResponseDto;
import com.app.dtos.bookdto.ScrapYardRequestDto;
import com.app.dtos.bookdto.ScrapYardRequestDto;

public interface ScrapYardService {

	//ScrapYardResponseDto createBook(ScrapYardRequestDto scrapYardRequestDto);
	
	List<ScrapYardPartsResponseDto> getAllBooks();
	/*ScrapYardResponseDto getBookById(Long bookId);
	ScrapYardResponseDto updateBook(Long bookId, ScrapYardRequestDto scrapYardRequestDto);
	void deleteBook(Long bookId);
	
	ScrapYardResponseDto addWriterToBook(Long bookId, Long writerId);*/


	List<ScrapYardPartsResponseDto> getPartByName(String partName);
}
