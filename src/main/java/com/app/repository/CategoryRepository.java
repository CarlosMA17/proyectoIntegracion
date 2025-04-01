package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.PartCategory;
import com.app.entity.PartId;
import com.app.entity.ScrapYardParts;

@Repository
public interface CategoryRepository extends JpaRepository<PartCategory, Long> {

}
