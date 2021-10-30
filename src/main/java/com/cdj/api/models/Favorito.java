package com.cdj.api.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="favorito")
public class Favorito implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	@JsonIgnore
	private long id;

	@JsonView(Views.Intermediaria2.class)
	private long jogoid;

	@JsonView(Views.Intermediaria1.class)
	private long usuarioid;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getJogoid() {
		return jogoid;
	}

	public void setJogoid(long jogoid) {
		this.jogoid = jogoid;
	}

	public long getUsuarioid() {
		return usuarioid;
	}

	public void setUsuarioid(long usuarioid) {
		this.usuarioid = usuarioid;
	}
	
}