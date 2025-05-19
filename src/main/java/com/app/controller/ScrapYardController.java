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
import com.app.dtos.scrapyarddto.ScrapYardResponseDto;
import com.app.dtos.scrapyardpartsdto.ScrapYardPartsRequestDto;
import com.app.repository.ScrapYardPartsRepository;
import com.app.service.ScrapYardServiceImpl;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class ScrapYardController {

    private final ScrapYardPartsRepository scrapYardPartsRepository;

	private static final String PART_RESOURCE = "/scrapyardparts";
	private static final String PART_SY_ID_PATH = PART_RESOURCE + "/scrapyards/{scrapYardId}";
	private static final String PART_PART_ID_PATH = PART_RESOURCE + "/{partId}";
	private static final String PART_RESERVATION_PATH = PART_RESOURCE + "/scrapyards/reservation/{scrapYardPartId}";
	private static final String PART_DELETE_PATH = PART_RESOURCE + "/scrapyards/delete/{scrapYardPartId}";
	private static final String SCRAPYARD_PART_ID_PATH = "/scrapyards/bypartid/{scrapYardPartId}";
	
	@Autowired
	ScrapYardServiceImpl scrapYardService;


    ScrapYardController(ScrapYardPartsRepository scrapYardPartsRepository) {
        this.scrapYardPartsRepository = scrapYardPartsRepository;
    }

	
	/**
	 *  GET ALL PARTS
	 */
	@GetMapping(value = PART_SY_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<ApiResponseDto<List<ScrapYardPartsResponseDto>>> getAllParts(@PathVariable Long scrapYardId) {

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
	 * GET SCRAPYARD BY SCRAPYARDPARTID
	 * @param partId
	 * @return
	 */
	@GetMapping(value = SCRAPYARD_PART_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponseDto<ScrapYardResponseDto>> getScrapYardByPartId(@PathVariable Long scrapYardPartId) {		
		ScrapYardResponseDto parts = scrapYardService.getScrapYardByPartId(scrapYardPartId);
		
		ApiResponseDto<ScrapYardResponseDto> response = new ApiResponseDto<>("Book fetched successfully",
				HttpStatus.OK.value(), parts);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 *  RESTOCK RESERVATION
	 * @param partName
	 */
	@PutMapping(value = PART_RESERVATION_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponseDto<Void>> restockScrapYardPart(@PathVariable Long scrapYardPartId) {	

	    
	    scrapYardService.restockScrapYardPart(scrapYardPartId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 *  DELETE PART BY SCRAPYARDPARTSiD
	 * @param partName
	 */
	@DeleteMapping(value = PART_DELETE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deletePart(@PathVariable Long scrapYardPartId) {		
		scrapYardService.deletePart(scrapYardPartId);
		
		
	    return ResponseEntity.noContent().build();
	}
	
	/**
	 * 
	 * @param scrapyardPartsRequestDto
	 * @return
	 */
	@PostMapping(value = PART_RESOURCE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addPart(@RequestBody ScrapYardPartsRequestDto scrapyardPartsRequestDto) {
		System.out.println(scrapyardPartsRequestDto);
		scrapYardService.addPart(scrapyardPartsRequestDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
