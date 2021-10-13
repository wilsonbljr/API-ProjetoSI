package com.cdj.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cdj.api.models.Developer;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
	
	Page<Developer> findAll(Pageable pageable);
	
	Developer findById(long id);
	
	Page<Developer> findByDescricaoContaining(String descricao, Pageable pageable);
}
