package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.accesodatos.entity.Writer;

public interface WriterRepository extends JpaRepository<Writer, Long> {}

