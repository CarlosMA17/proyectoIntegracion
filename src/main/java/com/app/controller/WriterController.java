package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.api.ApiResponseDto;
import com.app.dtos.writerdto.WriterResponseDto;
import com.app.service.WriterServiceImpl;



@RestController
@RequestMapping("/api/v1")
public class WriterController {
	@Autowired private WriterServiceImpl writerService;
    
    private final String WRITER_RESOURCE = "/writers";
    private final String WRITER_ID_PATH = WRITER_RESOURCE + "/{writerId}";
    
    @GetMapping(value = WRITER_ID_PATH,
	 produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponseDto<WriterResponseDto>> getAll(@PathVariable Long writerId) {
    	WriterResponseDto writer = writerService.getWriterById(writerId);
		ApiResponseDto<WriterResponseDto> response= new ApiResponseDto<>("writers fetched succesfully", HttpStatus.OK.value(), writer);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
