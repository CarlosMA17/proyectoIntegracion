package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entity.Car;
import com.app.entity.Part;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
	@Query("SELECT p FROM Part p JOIN p.cars c WHERE c.carId = :carId")
    List<Part> findPartsByCarId(@Param("carId") Long carId);

}
