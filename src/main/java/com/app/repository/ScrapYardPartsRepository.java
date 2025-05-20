package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Part;
import com.app.entity.ScrapYard;
import com.app.entity.ScrapYardParts;

@Repository
public interface ScrapYardPartsRepository extends JpaRepository<ScrapYardParts, Long > {

	List<ScrapYardParts> findByPartIdNameStartingWith(String partName);
	List<ScrapYardParts> findByPartId_PartIdAndReservedFalse(Long partId);
	List<ScrapYardParts> findByScrapYard_ScrapYardId(Long scrapYardId);
	ScrapYardParts findByReservation_ReservationId(Long reservationId);

}
