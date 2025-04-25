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
import com.app.dtos.carsdtos.CarResponseDto;
import com.app.dtos.categorydto.CategoryResponseDto;
import com.app.service.CarServiceImpl;
import com.app.service.CategoryServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class CarController {
	
	private static final String CAR_RESOURCE = "/cars";
	private static final String CAR_BRAND = CAR_RESOURCE + "/{brand}";
	private static final String CAR_BRAND_MODEL = CAR_BRAND + "/{model}";
	private static final String CAR_BRAND_MODEL_MOTOR = CAR_BRAND_MODEL + "/{motor}";


	@Autowired
	CarServiceImpl carService;
	
	
	
	/**
	 *  GET ALL CATEGORIES
+	 */
	@GetMapping(value = CAR_RESOURCE, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<ApiResponseDto<List<CarResponseDto>>> getAllBrands() {
		List<CarResponseDto> brands = carService.getAllCars();

		ApiResponseDto<List<CarResponseDto>> response = new ApiResponseDto<>("Book fetched successfully",
				HttpStatus.OK.value(), brands);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
