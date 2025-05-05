package com.app.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.api.ApiResponseDto;
import com.app.dtos.scrapyarddto.ScrapYardPartsResponseDto;
import com.app.dtos.scrapyarddto.ScrapYardRequestDto;
import com.app.service.ScrapYardServiceImpl;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class ScrapYardController {

	private static final String PART_RESOURCE = "/scrapyardparts";
	private static final String PART_SY_ID_PATH = PART_RESOURCE + "/scrapyards/{scrapYardId}";
	private static final String PART_PART_ID_PATH = PART_RESOURCE + "/{partId}";
	
	@Autowired
	ScrapYardServiceImpl scrapYardService;

	
	/**
	 *  GET ALL PARTS
	 */
	@GetMapping(value = PART_SY_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<ApiResponseDto<List<ScrapYardPartsResponseDto>>> getAllParts(@PathVariable Long scrapYardId) {
		System.out.println(scrapYardId);
		List<ScrapYardPartsResponseDto> scrapYardParts = scrapYardService.getAllPartsBySY(scrapYardId);
		ApiResponseDto<List<ScrapYardPartsResponseDto>> response = new ApiResponseDto<>("parts fetched successfully",
				HttpStatus.OK.value(), scrapYardParts);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

	/**
	 *  GET PART BY SUBCATEGORY ID
	 * @param partName
	 */
	@GetMapping(value = PART_PART_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponseDto<List<ScrapYardPartsResponseDto>>> getPartBySubcategoryId(@PathVariable Long partId) {		
		List<ScrapYardPartsResponseDto> parts = scrapYardService.getPartBySubcategoryId(partId);
		
		ApiResponseDto<List<ScrapYardPartsResponseDto>> response = new ApiResponseDto<>("Book fetched successfully",
				HttpStatus.OK.value(), parts);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 *  GET PART BY NAME
	 * @param partName
	 *
	@GetMapping(value = PART_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponseDto<List<ScrapYardPartsResponseDto>>> getPartByName(@PathParam(value = "partName") String partName) {		
		List<ScrapYardPartsResponseDto> parts = scrapYardService.getPartByName(partName);
		
		ApiResponseDto<List<ScrapYardPartsResponseDto>> response = new ApiResponseDto<>("Book fetched successfully",
				HttpStatus.OK.value(), parts);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}*/

}
