package com.cdj.api.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="lista_personalizada")
public class ListaPersonalizada implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	@JsonView(Views.External.class)
	private long id;
	
	@JsonView(Views.External.class)
	private String nome;
	
	@JsonView(Views.External.class)
	private String descricao;
	
	
	@ManyToMany
	@JoinTable(
			name="jogo_lista",
			joinColumns = @JoinColumn(name = "listaid"),
			inverseJoinColumns = @JoinColumn(name = "jogoid"))
	@JsonView(Views.Internal.class)
	private Set<Jogo> jogos = new HashSet<>();
	
	
	@ManyToOne
	@JoinColumn(name = "usuarioid", referencedColumnName = "id")
	@JsonView(Views.Internal.class)
	private Usuario usuario;
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Set<Jogo> getJogos() {
		return jogos;
	}


	public void setJogos(Set<Jogo> jogos) {
		this.jogos = jogos;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
