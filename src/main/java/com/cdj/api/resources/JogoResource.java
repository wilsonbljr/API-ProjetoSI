package com.cdj.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdj.api.models.Jogo;
import com.cdj.api.models.Score;
import com.cdj.api.models.Views;
import com.cdj.api.repository.JogoRepository;
import com.cdj.api.repository.ScoreRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value="/api")
public class JogoResource {

	@Autowired
	JogoRepository jogoRepository;
	
	@Autowired
	ScoreRepository scoreRepository;
	
	@GetMapping("/jogos")
	@JsonView(Views.Internal.class)
	public Page<Jogo> listaJogos(Pageable pageable){
		return jogoRepository.findAll(pageable);
	}
	
	@GetMapping("/jogos/{id}")
	@JsonView(Views.Internal.class)
	public Jogo listaJogoUnico(@PathVariable(value="id") long id){
		return jogoRepository.findById(id);
	}
	
	@PostMapping("/jogos")
	@JsonView(Views.Internal.class)
	public ResponseEntity<Jogo> salvaJogo(@RequestBody Jogo jogo) {
		jogoRepository.save(jogo);
		return new ResponseEntity<Jogo>(jogo, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/jogos/{id}")
	public void deletaJogo(@PathVariable(value="id") long id) {
		jogoRepository.deleteById(id);
	}
	
	@PutMapping("/jogos")
	@JsonView(Views.Internal.class)
	public ResponseEntity<?> atualizaJogo(@RequestBody Jogo jogo) {
		if (jogoRepository.findById(jogo.getId()) != null) {
			jogoRepository.save(jogo);
			return new ResponseEntity<Jogo>(jogo, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	

	@GetMapping("/jogos/{id}/scores")
	@JsonView(Views.External.class)
	public Page<Score> listaScoresByJogo(@PathVariable(value="id") long id, Pageable pageable){ 
		Page<Score> lista = scoreRepository.findByJogoId(id, pageable); return lista; }
	

	@GetMapping("/jogos/{id}/reviews")
	@JsonView(Views.External.class)
	public Page<Score> listaReviewsByJogo(@PathVariable(value="id") long id, Pageable pageable) {
		return scoreRepository.findByJogoId(id, pageable);
	}
	
	@GetMapping("/plataformas/{id}/jogos")
	@JsonView(Views.External.class)
	public Page<Jogo> listaJogosByPlataforma(@PathVariable(value="id") long id, Pageable pageable) {
		Page<Jogo> lista = jogoRepository.findByPlataformaId(id, pageable);
		return lista;
	}
}
