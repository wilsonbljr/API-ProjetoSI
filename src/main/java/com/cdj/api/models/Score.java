package com.cdj.api.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="score")
public class Score implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	@JsonView(Views.External.class)
	private long id;
	
	@JsonView(Views.External.class)
	private long nota;
	
	
	@ManyToOne
	@JoinColumn(name = "jogoid", referencedColumnName ="id")
	@JsonView(Views.External.class)
	private Jogo jogo;
	
	
	@ManyToOne
	@JoinColumn(name = "usuarioid", referencedColumnName ="id")
	@JsonView(Views.External.class)
	private Usuario usuario;
	
	
	
	@OneToOne(optional = true, cascade=CascadeType.MERGE, orphanRemoval = true)
	@JoinColumn(name = "reviewid", referencedColumnName ="id")
	@JsonView(Views.External.class)
	private Review review;
	
	@JsonIgnore
	@OneToMany(mappedBy = "score", cascade=CascadeType.REMOVE, orphanRemoval = true)
	private Set<Gostei> gostei = new HashSet<>();
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getNota() {
		return nota;
	}


	public void setNota(long nota) {
		this.nota = nota;
	}


	public Jogo getJogo() {
		return jogo;
	}


	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Review getReview() {
		return review;
	}


	public void setReview(Review review) {
		this.review = review;
	}


	public Set<Gostei> getGosteis() {
		return gostei;
	}


	public void setLikes(Set<Gostei> gostei) {
		this.gostei = gostei;
	}
	
}
