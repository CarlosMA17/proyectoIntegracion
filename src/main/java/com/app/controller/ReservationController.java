package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	 *  GET ALL RESERVATIONS
	 */
	@GetMapping(value = "/{reservationId}",  produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<ApiResponseDto<List<ReservationResponseDto>>> getAllReservations(@PathVariable Long reservationId) {

		List<ReservationResponseDto> reservations = reservationService.getAllReservations(reservationId);
		
		ApiResponseDto<List<ReservationResponseDto>> response = new ApiResponseDto<>("parts fetched successfully",
				HttpStatus.OK.value(), reservations);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
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
