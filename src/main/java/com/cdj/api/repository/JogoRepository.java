package com.cdj.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cdj.api.models.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
	
	Page<Jogo> findAll(Pageable pageable);
	
	Jogo findById(long id);
	
	Page<Jogo> findByPlataformaId(long id, Pageable pageable);
}
