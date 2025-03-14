package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accesodatos.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {}

