package com.cdj.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cdj.api.models.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {
	
	Page<Score> findAll(Pageable pageable);
	
	Score findById(long id);
	
	Page<Score> findByJogoId(long id, Pageable pageable);
	
	List<Score> findByJogoIdAndUsuarioId(long jogoid, long usuarioid);
	
}

