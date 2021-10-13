package com.cdj.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cdj.api.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	Review findById(long id);
	
	Page<Review> findAll(Pageable pageable);
}
