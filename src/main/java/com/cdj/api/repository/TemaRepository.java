package com.cdj.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cdj.api.models.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long> {
	
	Page<Tema> findAll(Pageable pageable);
	
	Tema findById(long id);
	
	Page<Tema> findByDescricaoContaining(String descricao, Pageable pageable);
}
