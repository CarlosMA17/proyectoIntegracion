package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.api.ApiResponseDto;
import com.app.dtos.partdto.PartResponseDto;
import com.app.service.CarServiceImpl;
import com.app.service.PartServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class PartController {
	
	private static final String PART_RESOURCE = "/parts/{carId}";


	@Autowired
	PartServiceImpl partService;
	
	
	
	/**
	 *  GET ALL PARTS BY CAR
+	 */
	@GetMapping(value = PART_RESOURCE, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<ApiResponseDto<List<PartResponseDto>>> getAllParts(@PathVariable long carId) {
		List<PartResponseDto> brands = partService.getAllPartsByCar(carId);

		ApiResponseDto<List<PartResponseDto>> response = new ApiResponseDto<>("Book fetched successfully",
				HttpStatus.OK.value(), brands);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
