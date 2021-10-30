package com.cdj.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cdj.api.models.Favorito;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
	
	Favorito findById(long id);
	
	Page<Favorito> findByJogoid (long id, Pageable pageable);
	
	Page<Favorito> findByUsuarioid(long id, Pageable pageable);
	
	Favorito findByJogoidAndUsuarioid(long id, long id2);
}
