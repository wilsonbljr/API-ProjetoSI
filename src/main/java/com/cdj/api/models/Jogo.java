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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="jogo")
public class Jogo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	@JsonView(Views.External.class)
	private long id;
	
	@JsonView(Views.External.class)
	private String titulo;
	
	@JsonView(Views.Internal.class)
	private String datalancamento;
	
	@JsonView(Views.Internal.class)
	private String resumo;
	
	@JsonView(Views.Internal.class)
	private String imagem;
	
	@JsonView(Views.Internal.class)
	private String trailer;
	
	@ManyToOne
	@JoinColumn(name = "temaid", referencedColumnName ="id")
	@JsonView(Views.Internal.class)
	private Tema tema;
	

	@ManyToOne
	@JoinColumn(name = "generoid", referencedColumnName ="id")
	@JsonView(Views.Internal.class)
	private Genero genero;
	

	@ManyToOne
	@JoinColumn(name = "publisherid", referencedColumnName ="id")
	@JsonView(Views.Internal.class)
	private Publisher publisher;


	@ManyToOne
	@JoinColumn(name = "developerid", referencedColumnName ="id")
	@JsonView(Views.Internal.class)
	private Developer developer;
	
	
	@ManyToMany(mappedBy="jogos")
	@JsonView(Views.Internal.class)
	private Set<Plataforma> plataforma = new HashSet<>();
	
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name="favorito",
			joinColumns = @JoinColumn(name = "jogoid"),
			inverseJoinColumns = @JoinColumn(name = "usuarioid"))
	private Set<Usuario> usuario = new HashSet<>();
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name="jogo_lista",
			joinColumns = @JoinColumn(name = "jogoid"),
			inverseJoinColumns = @JoinColumn(name = "listaid"))
	private Set<ListaPersonalizada> listapersonalizada = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "jogo")
	private Set<Score> scores = new HashSet<>();
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Developer getDeveloper() {
		return developer;
	}


	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	
	
	public Tema getTema() {
		return tema;
	}


	public void setTema(Tema tema) {
		this.tema = tema;
	}


	public Genero getGenero() {
		return genero;
	}


	public void setGenero(Genero genero) {
		this.genero = genero;
	}


	public Publisher getPublisher() {
		return publisher;
	}


	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	public Set<Plataforma> getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Set<Plataforma> plataforma) {
		this.plataforma = plataforma;
	}
	

	public Set<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(Set<Usuario> usuario) {
		this.usuario = usuario;
	}
	

	public Set<ListaPersonalizada> getListapersonalizada() {
		return listapersonalizada;
	}


	public void setListapersonalizada(Set<ListaPersonalizada> listapersonalizada) {
		this.listapersonalizada = listapersonalizada;
	}



	public Set<Score> getScores() {
		return scores;
	}


	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDatalancamento() {
		return datalancamento;
	}


	public void setDatalancamento(String datalancamento) {
		this.datalancamento = datalancamento;
	}


	public String getImagem() {
		return imagem;
	}


	public void setImagem(String imagem) {
		this.imagem = imagem;
	}


	public String getTrailer() {
		return trailer;
	}


	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}


	public String getResumo() {
		return resumo;
	}


	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	
	
}
