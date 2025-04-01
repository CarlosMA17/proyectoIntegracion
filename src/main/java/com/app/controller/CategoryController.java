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
import com.app.dtos.categorydto.CategoryResponseDto;
import com.app.dtos.scrapyarddto.ScrapYardPartsResponseDto;
import com.app.service.CategoryServiceImpl;
import com.app.service.ScrapYardServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {
	
	private static final String CATEGORY_RESOURCE = "/categories";
	private static final String CATEGORY_CAR = CATEGORY_RESOURCE + "/{carId}";
	
	@Autowired
	CategoryServiceImpl categoryService;
	
	/**
	 *  GET ALL CATEGORIES
	 *  @param carID
	 */
	@GetMapping(value = CATEGORY_CAR, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<ApiResponseDto<List<CategoryResponseDto>>> getAllCategories(@PathVariable long carId) {
		List<CategoryResponseDto> categories = categoryService.getAllCategoriesByCar(carId);

		ApiResponseDto<List<CategoryResponseDto>> response = new ApiResponseDto<>("Book fetched successfully",
				HttpStatus.OK.value(), categories);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
