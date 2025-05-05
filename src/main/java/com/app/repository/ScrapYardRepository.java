package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.PartId;
import com.app.entity.ScrapYard;
import com.app.entity.ScrapYardParts;

public interface ScrapYardRepository extends JpaRepository<ScrapYard, Long> {

}
