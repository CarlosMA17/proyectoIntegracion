package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dtos.reservation.ReservationRequestDto;
import com.app.dtos.reservation.ReservationResponseDto;
import com.app.entity.Reservation;
import com.app.entity.ScrapYardParts;
import com.app.entity.UserEntity;
import com.app.exception.ResourceNotFoundException;
import com.app.mappers.reservation.ReservationMapper;
import com.app.mappers.scrapyardparts.ScrapYardPartsMapper;
import com.app.repository.ReservationRepository;
import com.app.repository.ScrapYardPartsRepository;
import com.app.repository.UserRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired ScrapYardPartsRepository scrapYardPartsRepository;
	@Autowired UserRepository userRepository;
	@Autowired ReservationRepository reservationRepository;
	@Autowired ReservationMapper reservationMapper;
	@Autowired ScrapYardPartsMapper scrapYardPartsMapper;

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

	@Override
	public List<ReservationResponseDto> getAllReservations(Long userId) {

		UserEntity user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found" ));
		
		List<Reservation> reservationsResponse = reservationRepository.findByUserUserId(userId);
		
		List<ReservationResponseDto> reservations = reservationsResponse.stream()
			    .map(reservation -> {
			        ReservationResponseDto dto = reservationMapper.toResponse(reservation);
			        dto.setUserId(user.getUserId());
			        dto.setScrapYardPart(scrapYardPartsMapper.toResponse(reservation.getScrapYardPart()));
			        return dto;
			    })
			    .collect(Collectors.toList());
		
		return reservations;
	}
	
	@Override
	public void cancelReservation(Long reservationId) {
		
		
        ScrapYardParts part = scrapYardPartsRepository.findByReservations_ReservationId(reservationId);
                
        if (!part.isReserved()) {
            throw new RuntimeException("La pieza no esta reservada.");
        }

        part.setReserved(false);

		scrapYardPartsRepository.save(part);
		reservationRepository.deleteById(reservationId);
		
	}
	
	@Override
	public List<ReservationResponseDto> getAllReservationsByScrapYard(Long scrapYardId) {

		
		List<Reservation> reservationsResponse = reservationRepository.findByScrapYardPart_ScrapYardId_ScrapYardId(scrapYardId);
		
		List<ReservationResponseDto> reservations = reservationsResponse.stream()
			    .map(reservation -> {
			        ReservationResponseDto dto = reservationMapper.toResponse(reservation);
			        dto.setScrapYardPart(scrapYardPartsMapper.toResponse(reservation.getScrapYardPart()));
			        return dto;
			    })
			    .collect(Collectors.toList());
		
		return reservations;
	}
}
