package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dtos.reservation.ReservationRequestDto;
import com.app.dtos.reservation.ReservationResponseDto;
import com.app.entity.Reservation;
import com.app.entity.ScrapYardParts;
import com.app.entity.UserEntity;
import com.app.exception.ResourceNotFoundException;
import com.app.mappers.reservation.ReservationMapper;
import com.app.repository.ReservationRepository;
import com.app.repository.ScrapYardPartsRepository;
import com.app.repository.UserRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired ScrapYardPartsRepository scrapYardPartsRepository;
	@Autowired UserRepository userRepository;
	@Autowired ReservationRepository reservationRepository;
	@Autowired ReservationMapper reservationMapper;

	@Override
	public ReservationResponseDto createReservation(ReservationRequestDto reservationRequestDto) {

		UserEntity user = userRepository.findById(reservationRequestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + reservationRequestDto.getUserId()));

        ScrapYardParts part = scrapYardPartsRepository.findById(reservationRequestDto.getScrapYardPartId())
                .orElseThrow(() -> new ResourceNotFoundException("ScrapYardPart not found with id: " + reservationRequestDto.getScrapYardPartId()));

        Reservation reservation = reservationMapper.toEntity(reservationRequestDto);
        reservation.setUser(user);
        reservation.setScrapYardPart(part);

        return reservationMapper.toResponse(reservationRepository.save(reservation));
	}

}
