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

import com.cdj.api.models.Score;
import com.cdj.api.models.Views;
import com.cdj.api.repository.ScoreRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value="/api")
public class ScoreResource {

	@Autowired
	ScoreRepository scoreRepository;
	
	@JsonView(Views.External.class)
	@GetMapping("/scores")
	public Page<Score> listaScores(Pageable pageable){
		return scoreRepository.findAll(pageable);
	}
	
	
	@JsonView(Views.External.class)
	@GetMapping("/scores/{id}")
	public Score listaScoreUnico(@PathVariable(value="id") long id) {
		return scoreRepository.findById(id);
	}
	
	
	@PostMapping("/scores")
	public ResponseEntity<Score> salvaScore(@RequestBody Score score) {
		scoreRepository.save(score);
		return new ResponseEntity<Score>(score, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/scores/{id}")
	public void deletaScore(@PathVariable(value="id") long id) {
		scoreRepository.deleteById(id);
	}
	
	
	@PutMapping("/scores")
	public ResponseEntity<?> atualizaScore(@RequestBody Score score) {
		if (scoreRepository.findById(score.getId()) != null) {
			scoreRepository.save(score);
			return new ResponseEntity<Score>(score, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
