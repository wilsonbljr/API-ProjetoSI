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

import com.cdj.api.models.Developer;
import com.cdj.api.models.Views;
import com.cdj.api.repository.DeveloperRepository;
import com.cdj.api.repository.JogoRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value="/api")
public class DeveloperResource {

	@Autowired
	DeveloperRepository developerRepository;
	
	@Autowired
	JogoRepository jogoRepository;
	

	@GetMapping("/developers")
	@JsonView(Views.Internal.class)
	public Page<Developer> listaDevelopers(@RequestParam(required = false) String descricao, Pageable pageable) {
		return (descricao) != null ? developerRepository.findByDescricaoContaining(descricao, pageable)
				: developerRepository.findAll(pageable);
	}
	
	
	@GetMapping("/developers/{id}")
	@JsonView(Views.Internal.class)
	public Developer listaDeveloperUnico(@PathVariable(value="id") long id){
		return developerRepository.findById(id);
	}
	
	
	@PostMapping("/developers")
	@JsonView(Views.Internal.class)
	public ResponseEntity<Developer> salvaDeveloper(@RequestBody Developer developer) {
		developerRepository.save(developer);
		return new ResponseEntity<Developer>(developer, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/developers/{id}")
	public void deletaDeveloper(@PathVariable(value="id") long id) {
		developerRepository.deleteById(id);
	}
	
	
	@PutMapping("/developers")
	@JsonView(Views.Internal.class)
	public ResponseEntity<?> atualizaDeveloper(@RequestBody Developer developer) {
		if (developerRepository.findById(developer.getId()) != null) {
			developerRepository.save(developer);
			return new ResponseEntity<Developer>(developer, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
