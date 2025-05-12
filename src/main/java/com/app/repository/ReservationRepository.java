package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Reservation;

@Repository
public interface ReservationRepository  extends JpaRepository<Reservation, Long>{
	List<Reservation> findByUserUserId(Long userId);
}
