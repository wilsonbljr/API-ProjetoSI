package com.cdj.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cdj.api.models.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
	
	Genero findById(long id);
	
	Page<Genero> findAll(Pageable pageable);
	
	Page<Genero> findByDescricaoContaining(String descricao, Pageable pageable);
}
