package com.cdj.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdj.api.models.Gostei;

public interface GosteiRepository extends JpaRepository<Gostei, Long> {
	
	Gostei findById(long id);
	List<Gostei> findByUsuarioId (long id);
	List<Gostei> findByScoreId(long id);
	Gostei findByScoreIdAndUsuarioId(long id, long id2);
}
