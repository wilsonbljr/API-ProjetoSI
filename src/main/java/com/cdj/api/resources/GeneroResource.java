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

import com.cdj.api.models.Genero;
import com.cdj.api.models.Views;
import com.cdj.api.repository.GeneroRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value="/api")
public class GeneroResource {

	@Autowired
	GeneroRepository generoRepository;
	
	@GetMapping("/generos")
	@JsonView(Views.Internal.class)
	public Page<Genero> listaGeneros(String descricao, Pageable pageable){
		return (descricao) != null ? generoRepository.findByDescricaoContaining(descricao, pageable)
				: generoRepository.findAll(pageable);
	}
	
	@GetMapping("/generos/{id}")
	@JsonView(Views.Internal.class)
	public Genero listaGeneroUnico(@PathVariable(value="id") long id){
		return generoRepository.findById(id);
	}
	
	@PostMapping("/generos")
	@JsonView(Views.Internal.class)
	public ResponseEntity<Genero> salvaGenero(@RequestBody Genero genero) {
		generoRepository.save(genero);
		return new ResponseEntity<Genero>(genero, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/generos/{id}")
	public void deletaGenero(@PathVariable(value="id") long id) {
		generoRepository.deleteById(id);
	}
	
	@PutMapping("/generos")
	@JsonView(Views.Internal.class)
	public ResponseEntity<?> atualizaGenero(@RequestBody Genero genero) {
		if (generoRepository.findById(genero.getId()) != null) {
			generoRepository.save(genero);
			return new ResponseEntity<Genero>(genero, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
