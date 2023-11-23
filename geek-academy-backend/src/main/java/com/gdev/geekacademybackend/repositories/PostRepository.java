package com.gdev.geekacademybackend.repositories;

import com.gdev.geekacademybackend.models.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
