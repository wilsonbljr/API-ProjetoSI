package com.cdj.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cdj.api.models.ListaPersonalizada;

public interface ListaPersonalizadaRepository extends JpaRepository<ListaPersonalizada, Long> {
	
	Page<ListaPersonalizada> findAll(Pageable pageable);
	
	ListaPersonalizada findById(long id);
	
	Page<ListaPersonalizada> findByNomeContaining(String nome, Pageable pageable);
	
	Page<ListaPersonalizada> findByUsuarioId(long id, Pageable pageable);
}
