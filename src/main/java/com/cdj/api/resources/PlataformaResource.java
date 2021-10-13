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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdj.api.models.Plataforma;
import com.cdj.api.models.Views;
import com.cdj.api.repository.JogoRepository;
import com.cdj.api.repository.PlataformaRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value="/api")
public class PlataformaResource {

	@Autowired
	PlataformaRepository plataformaRepository;
	
	@Autowired
	JogoRepository jogoRepository;
	
	@GetMapping("/plataformas")
	@JsonView(Views.Internal.class)
	public Page<Plataforma> listaPlataformas(@RequestParam(required = false) String descricao, Pageable pageable){
		return (descricao) != null ? plataformaRepository.findByDescricaoContaining(descricao, pageable) 
				: plataformaRepository.findAll(pageable);
	}
	
	@GetMapping("/plataformas/{id}")
	@JsonView(Views.Internal.class)
	public Plataforma listaPlataformaUnico(@PathVariable(value="id") long id){
		return plataformaRepository.findById(id);
	}
	
	@PostMapping("/plataformas")
	@JsonView(Views.Internal.class)
	public ResponseEntity<Plataforma> salvaPlataforma(@RequestBody Plataforma plataforma) {
		plataformaRepository.save(plataforma);
		return new ResponseEntity<Plataforma>(plataforma, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/plataformas/{id}")
	public void deletaPlataforma(@PathVariable(value="id") long id) {
		plataformaRepository.deleteById(id);
	}
	
	@PutMapping("/plataformas")
	@JsonView(Views.Internal.class)
	public ResponseEntity<?> atualizaPlataforma(@RequestBody Plataforma plataforma) {
		if (plataformaRepository.findById(plataforma.getId()) != null) {
			plataformaRepository.save(plataforma);
			return new ResponseEntity<Plataforma>(plataforma, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	
}
