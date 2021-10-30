package com.cdj.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdj.api.models.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
	
	Page<Jogo> findAll(Pageable pageable);
	
	Jogo findById(long id);
	
	Page<Jogo> findByPlataformaId(long id, Pageable pageable);
	
	@Query("SELECT DISTINCT j FROM Jogo j JOIN j.plataforma p WHERE (:titulo is null or UPPER(j.titulo) "
			+ "LIKE CONCAT('%',UPPER(:titulo),'%')) and (:tema is null or j.tema.id IN :tema) and "
			+ "(:genero is null or j.genero.id IN :genero) and (:publisher is null "
			+ "or j.publisher.id IN :publisher) and"
			+ "(:developer is null or j.developer.id IN :developer) AND "
			+ "(:plataforma is null or p.id IN :plataforma)")
	Page<Jogo> pesquisaJogo(@Param("titulo") String titulo, @Param("tema") List<Long> temaid,
			@Param("genero") List<Long> generoid, @Param("publisher") List<Long> publisherid, 
			@Param("developer") List<Long> developerid, @Param("plataforma") List<Long> plataformaid, Pageable pageable);
}
