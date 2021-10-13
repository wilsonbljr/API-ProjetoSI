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

import com.cdj.api.models.Review;
import com.cdj.api.models.Score;
import com.cdj.api.models.Views;
import com.cdj.api.repository.ReviewRepository;
import com.cdj.api.repository.ScoreRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value="/api")
public class ReviewResource {

	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	ScoreRepository scoreRepository;
	
	@JsonView(Views.External.class)
	@GetMapping("/reviews")
	public Page<Review> listaReview(Pageable pageable){
		return reviewRepository.findAll(pageable);
	}
	
	
	@JsonView(Views.Internal.class)
	@GetMapping("/reviews/{id}")
	public Review listaReviewUnica(@PathVariable(value="id") long id) {
		return reviewRepository.findById(id);
	}
	
	
	@PostMapping("/reviews")
	public ResponseEntity<?> salvaReview(@RequestBody Review review) {
		
		Score score = new Score();
		score.setJogo(review.getScore().getJogo());
		score.setNota(review.getScore().getNota());
		score.setUsuario(review.getScore().getUsuario());
		
		final Review reviewIsolada = new Review();
		reviewIsolada.setReview(review.getReview());
		
		if (scoreRepository.findByJogoIdAndUsuarioId(review.getScore().getJogo().getId(), review.getScore().getUsuario().getId()).isEmpty()) {
			final Review reviewCadastrada = reviewRepository.save(reviewIsolada);
			
			score.setReview(reviewCadastrada); 
		
			scoreRepository.save(score);
			return new ResponseEntity<Score>(score, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@DeleteMapping("/reviews/{id}")
	public void deletaReview(@PathVariable(value="id") long id) {
		final Review review = reviewRepository.findById(id);
		final Score score = scoreRepository.findById(review.getScore().getId());
		scoreRepository.deleteById(score.getId());
	}
	
	
	@PutMapping("/reviews")
	public ResponseEntity<?> atualizaReview(@RequestBody Review review) {
		long idAtual = review.getScore().getId();
		Score scoreAtual = scoreRepository.findById(idAtual);
		Score scoreNovo = new Score();
		scoreNovo.setId(review.getScore().getId());
		scoreNovo.setJogo(review.getScore().getJogo());
		scoreNovo.setNota(review.getScore().getNota());
		scoreNovo.setUsuario(review.getScore().getUsuario());
		
		final Review reviewIsolada = new Review();
		reviewIsolada.setId(review.getId());
		reviewIsolada.setReview(review.getReview());
		
		scoreNovo.setReview(reviewIsolada);
		
		if(scoreAtual.getJogo().getId() == scoreNovo.getJogo().getId() && scoreAtual.getUsuario().getId() == scoreNovo.getUsuario().getId() ) {
			scoreRepository.save(scoreNovo);
			return new ResponseEntity<Score>(scoreNovo, HttpStatus.CREATED);
		} else {
			if (scoreRepository.findByJogoIdAndUsuarioId(scoreNovo.getJogo().getId(), scoreNovo.getUsuario().getId()).isEmpty()) {
				scoreRepository.save(scoreNovo);
				return new ResponseEntity<Score>(scoreNovo, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		}		
	}

	
}