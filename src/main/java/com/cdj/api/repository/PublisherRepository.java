package com.cdj.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cdj.api.models.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
	
	Page<Publisher> findByDescricaoContaining(String descricao, Pageable pageable);
	
	Page<Publisher> findAll(Pageable pageable);
	
	Publisher findById(long id);
}
