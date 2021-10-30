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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdj.api.models.ListaJogo;
import com.cdj.api.models.Views;
import com.cdj.api.repository.ListaJogoRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value="/api")
public class ListaJogoResource {

	@Autowired
	ListaJogoRepository listaJogoRepository;
	
	@GetMapping("/listaspersonalizadas/{id}/jogos")
	@JsonView(Views.Intermediaria2.class)
	public Page<ListaJogo> listaJogosNaLista(@PathVariable(value="id") long id, Pageable pageable){
		return listaJogoRepository.findByListaid(id, pageable);
	}
	
	@GetMapping("/jogos/{id}/listaspersonalizadas")
	@JsonView(Views.Intermediaria1.class)
	public Page<ListaJogo> listaListasComJogo(@PathVariable(value="id") long id, Pageable pageable){
		return listaJogoRepository.findByJogoid(id, pageable);
	}
	
	@PostMapping("/listajogo")
	@JsonView(Views.Intermediaria3.class)
	public ResponseEntity<?> salvaListaJogo(@RequestBody ListaJogo listajogo) {
		if (listaJogoRepository.findByJogoidAndListaid(listajogo.getJogoid(), listajogo.getListaid()) != null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			listaJogoRepository.save(listajogo);
			return new ResponseEntity<ListaJogo>(listajogo, HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("/listajogo")
	public ResponseEntity<?> deletaListaJogo(@RequestBody ListaJogo listajogo){
		ListaJogo listaJogoDeletar = listaJogoRepository.findByJogoidAndListaid(listajogo.getJogoid(),listajogo.getListaid());
		if (listaJogoDeletar != null) {
			listaJogoRepository.deleteById(listaJogoDeletar.getId());
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
