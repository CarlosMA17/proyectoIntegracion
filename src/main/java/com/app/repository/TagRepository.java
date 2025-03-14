package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accesodatos.entity.Tag;
import java.util.List;
import java.util.Optional;


public interface TagRepository extends JpaRepository<Tag, Long> {
	Tag getByName(String name);
}

