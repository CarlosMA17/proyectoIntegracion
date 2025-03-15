package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Part;
import com.app.entity.PartId;
import com.app.entity.ScrapYard;
import com.app.entity.ScrapYardParts;

@Repository
public interface ScrapYardRepository extends JpaRepository<ScrapYardParts, PartId> {

	List<ScrapYardParts> findByPartIdNameStartingWith(String partName);
}
