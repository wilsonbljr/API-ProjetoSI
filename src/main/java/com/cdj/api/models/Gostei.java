package com.cdj.api.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="gostei")
public class Gostei implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	@JsonView(Views.Internal.class)
	private long id;


	@ManyToOne
	@JoinColumn(name = "usuarioid", referencedColumnName = "id")
	@JsonView(Views.External.class)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "scoreid", referencedColumnName = "id")
	@JsonView(Views.Internal.class)
	private Score score;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}
	
}