package com.cdj.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cdj.api.models.ListaJogo;

public interface ListaJogoRepository extends JpaRepository<ListaJogo, Long> {
	
	ListaJogo findById(long id);
	
	Page<ListaJogo> findByJogoid (long id, Pageable pageable);
	
	Page<ListaJogo> findByListaid(long id, Pageable pageable);
	
	ListaJogo findByJogoidAndListaid(long id, long id2);
}
