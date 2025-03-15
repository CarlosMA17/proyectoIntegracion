package com.app.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.api.ApiResponseDto;
import com.app.dtos.bookdto.ScrapYardPartsResponseDto;
import com.app.dtos.bookdto.ScrapYardRequestDto;
import com.app.service.ScrapYardServiceImpl;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api")
public class ScrapYardController {

	private static final String BOOK_RESOURCE = "/scrapyards";
	private static final String BOOK_ID_PATH = BOOK_RESOURCE + "/search";
	
	@Autowired
	ScrapYardServiceImpl scrapYardService;
	
	@GetMapping(BOOK_RESOURCE + "/ping")
	public ResponseEntity<String> pong() {
		return ResponseEntity.ok("pong scrapYard...");
	}

	
	/*@PostMapping(value = BOOK_RESOURCE,
	 		 consumes = MediaType.APPLICATION_JSON_VALUE, 
	 		 produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponseDto<BookResponseDto>> createBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
		
		BookResponseDto createdBook = bookService.createBook(bookRequestDto);
		ApiResponseDto<BookResponseDto> response = new ApiResponseDto("Book created successfully",
														HttpStatus.CREATED.value(), createdBook);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);        
	}*/
	
	@GetMapping(value = BOOK_RESOURCE, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<ApiResponseDto<List<ScrapYardPartsResponseDto>>> getAllBooks() {
		List<ScrapYardPartsResponseDto> scrapYardParts = scrapYardService.getAllBooks();

		ApiResponseDto<List<ScrapYardPartsResponseDto>> response = new ApiResponseDto<>("Book fetched successfully",
				HttpStatus.OK.value(), scrapYardParts);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@GetMapping(value = BOOK_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponseDto<List<ScrapYardPartsResponseDto>>> getBookById(@PathParam(value = "partName") String partName) {		
		List<ScrapYardPartsResponseDto> parts = scrapYardService.getPartByName(partName);
		
		ApiResponseDto<List<ScrapYardPartsResponseDto>> response = new ApiResponseDto<>("Book fetched successfully",
				HttpStatus.OK.value(), parts);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/*@PutMapping(value = BOOK_ID_PATH, 
				consumes = MediaType.APPLICATION_JSON_VALUE, 
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponseDto<BookResponseDto>> updateBook(@Valid @PathVariable Long bookId, 
																	  @RequestBody BookRequestDto bookRequestDto) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@DeleteMapping(value = BOOK_ID_PATH)
	public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	
	@PostMapping(value = BOOK_ID_PATH + "/writers/{writerId}",
		 	 produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponseDto<BookResponseDto>> addExistingWriterToBook(@PathVariable Long bookId, @PathVariable Long writerId) {																		
		
		BookResponseDto updatedBook = bookService.addWriterToBook(writerId, bookId);
		ApiResponseDto<BookResponseDto> response = new ApiResponseDto("Writer added successfully",
				HttpStatus.OK.value(), updatedBook);
		
		return new ResponseEntity<>(response, HttpStatus.OK);        
	}*/

}
