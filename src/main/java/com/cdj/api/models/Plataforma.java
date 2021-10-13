package com.cdj.api.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.JoinColumn;

@Entity
@Table(name="plataforma")
public class Plataforma implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	@JsonView(Views.External.class)
	private long id;
	
	@JsonView(Views.Internal.class)
	private String descricao;

	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name="jogo_plataforma",
			joinColumns = @JoinColumn(name = "plataformaid"),
			inverseJoinColumns = @JoinColumn(name = "jogoid"))
	private Set<Jogo> jogos = new HashSet<>();


	public Set<Jogo> getJogos() {
		return jogos;
	}


	public void setJogos(Set<Jogo> jogos) {
		this.jogos = jogos;
	}
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
}
