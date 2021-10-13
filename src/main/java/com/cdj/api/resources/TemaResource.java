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

import com.cdj.api.models.Tema;
import com.cdj.api.models.Views;
import com.cdj.api.repository.TemaRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value="/api")
public class TemaResource {

	@Autowired
	TemaRepository temaRepository;
	
	@GetMapping("/temas")
	@JsonView(Views.Internal.class)
	public Page<Tema> listaTemas(String descricao, Pageable pageable) {
		return (descricao) != null ? temaRepository.findByDescricaoContaining(descricao, pageable)
				: temaRepository.findAll(pageable);
	}
	
	@GetMapping("/temas/{id}")
	@JsonView(Views.Internal.class)
	public Tema listaTemaUnico(@PathVariable(value="id") long id) {
		return temaRepository.findById(id);
	}
	
	@PostMapping("/temas")
	@JsonView(Views.Internal.class)
	public ResponseEntity<Tema> salvaTema(@RequestBody Tema tema) {
		temaRepository.save(tema);
		return new ResponseEntity<Tema>(tema, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/temas/{id}")
	public void deletaTema(@PathVariable(value="id") long id) {
		temaRepository.deleteById(id);
	}
	
	@PutMapping("/temas")
	@JsonView(Views.Internal.class)
	public ResponseEntity<?> atualizaTema(@RequestBody Tema tema) {
		if (temaRepository.findById(tema.getId()) != null) {
			temaRepository.save(tema);
			return new ResponseEntity<Tema>(tema, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
