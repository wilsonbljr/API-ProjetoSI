package com.cdj.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cdj.api.models.Plataforma;

public interface PlataformaRepository extends JpaRepository<Plataforma, Long> {
	
	Page<Plataforma> findByDescricaoContaining(String descricao, Pageable pageable);
	
	Page<Plataforma> findAll(Pageable pageable);
	
	Plataforma findById(long id);
}
