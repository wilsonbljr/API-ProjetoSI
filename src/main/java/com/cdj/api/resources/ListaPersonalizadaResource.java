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

import com.cdj.api.models.ListaPersonalizada;
import com.cdj.api.models.Views;
import com.cdj.api.repository.ListaPersonalizadaRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value="/api")
public class ListaPersonalizadaResource {

	@Autowired
	ListaPersonalizadaRepository listapersonalizadaRepository;
	
	@GetMapping("/listaspersonalizadas")
	@JsonView(Views.External.class)
	public Page<ListaPersonalizada> listaListaPersonalizada(@RequestParam(required = false) String nome, Pageable pageable){
		return (nome) != null ? listapersonalizadaRepository.findByNomeContaining(nome, pageable)
				: listapersonalizadaRepository.findAll(pageable);
	}
	
	@GetMapping("/listaspersonalizadas/{id}")
	@JsonView(Views.Internal.class)
	public ListaPersonalizada listaListaPersonalizadaUnica(@PathVariable(value="id") long id) {
		return listapersonalizadaRepository.findById(id);
	}
	
	@PostMapping("/listaspersonalizadas")
	@JsonView(Views.Internal.class)
	public ResponseEntity<ListaPersonalizada> salvaListaPersonalizada(@RequestBody ListaPersonalizada listapersonalizada) {
		listapersonalizadaRepository.save(listapersonalizada);
		return new ResponseEntity<ListaPersonalizada>(listapersonalizada, HttpStatus.CREATED);
	}
	
	@GetMapping("/usuarios/{id}/listaspersonalizadas")
	@JsonView(Views.External.class)
	public Page<ListaPersonalizada> listaPersonalizadaPorUsuario(@PathVariable(value="id") long id, Pageable pageable) {
		return listapersonalizadaRepository.findByUsuarioId(id, pageable);
	}
	
	@DeleteMapping("/listaspersonalizadas/{id}")
	public void deletaListaPersonalizada(@PathVariable(value="id") long id) {
		listapersonalizadaRepository.deleteById(id);
	}
	
	@PutMapping("/listaspersonalizadas")
	@JsonView(Views.Internal.class)
	public ResponseEntity<?> atualizaListaPersonalizada(@RequestBody ListaPersonalizada listapersonalizada) {
		if (listapersonalizadaRepository.findById(listapersonalizada.getId()) != null) {
			listapersonalizadaRepository.save(listapersonalizada);
			return new ResponseEntity<ListaPersonalizada>(listapersonalizada, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}