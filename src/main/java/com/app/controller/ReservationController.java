package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.api.ApiResponseDto;
import com.app.dtos.reservation.ReservationRequestDto;
import com.app.dtos.reservation.ReservationResponseDto;
import com.app.dtos.scrapyarddto.ScrapYardPartsResponseDto;
import com.app.dtos.scrapyardpartsdto.ScrapYardPartsRequestDto;
import com.app.service.ReservationServiceImpl;
import com.app.service.ScrapYardServiceImpl;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
	
	@Autowired
	ReservationServiceImpl reservationService;
	
	/**
	 * CREATE NEW RESERVATION
	 * @param ReservationRequestDto
	 * @return
	 */
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponseDto<ReservationResponseDto>> addPart(@RequestBody ReservationRequestDto reservationRequestDto) {
		
		ReservationResponseDto reservation = reservationService.createReservation(reservationRequestDto);
		ApiResponseDto<ReservationResponseDto> response = new ApiResponseDto<>("reservation created successfully",
				HttpStatus.OK.value(), reservation);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
