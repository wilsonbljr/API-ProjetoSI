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

import com.cdj.api.models.Publisher;
import com.cdj.api.models.Views;
import com.cdj.api.repository.PublisherRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value="/api")
public class PublisherResource {

	@Autowired
	PublisherRepository publisherRepository;
	
	@GetMapping("/publishers")
	@JsonView(Views.Internal.class)
	public Page<Publisher> listaPublishers(@RequestParam(required = false) String descricao, Pageable pageable){
		return (descricao) != null ? publisherRepository.findByDescricaoContaining(descricao, pageable) 
				: publisherRepository.findAll(pageable);
	}
	
	@GetMapping("/publishers/{id}")
	@JsonView(Views.Internal.class)
	public Publisher listaPublisherUnico(@PathVariable(value="id") long id){
		return publisherRepository.findById(id);
	}
	
	@PostMapping("/publishers")
	@JsonView(Views.Internal.class)
	public ResponseEntity<Publisher> salvaPublisher(@RequestBody Publisher publisher) {
		publisherRepository.save(publisher);
		return new ResponseEntity<Publisher>(publisher, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/publishers/{id}")
	public void deletaPublisher(@PathVariable(value="id") long id) {
		publisherRepository.deleteById(id);
	}
	
	@PutMapping("/publishers")
	@JsonView(Views.Internal.class)
	public ResponseEntity<?> atualizaPublisher(@RequestBody Publisher publisher) {
		if (publisherRepository.findById(publisher.getId()) != null) {
			publisherRepository.save(publisher);
			return new ResponseEntity<Publisher>(publisher, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
