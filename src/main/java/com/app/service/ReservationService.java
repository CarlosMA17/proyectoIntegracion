package com.app.service;

import com.app.dtos.reservation.ReservationRequestDto;
import com.app.dtos.reservation.ReservationResponseDto;

public interface ReservationService {
	ReservationResponseDto createReservation(ReservationRequestDto reservationRequestDto);
}
