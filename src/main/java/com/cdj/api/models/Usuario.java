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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	@JsonView(Views.External.class)
	private long id;
	
	@JsonView(Views.Internal.class)
	private String nome;
	
	@JsonView(Views.Internal.class)
	private String sobrenome;
	
	@JsonView(Views.Internal.class)
	private String email;
	
	@JsonView(Views.Internal.class)
	private String senha;
	
	@JsonView(Views.External.class)
	private String nickname;
	
	@JsonView(Views.Internal.class)
	private Boolean admin;
	
	@JsonView(Views.Internal.class)
	private String imagem;
	
	
	@ManyToMany(cascade = CascadeType.DETACH)
	@JoinTable(
			name="favorito",
			joinColumns = @JoinColumn(name = "usuarioid"),
			inverseJoinColumns = @JoinColumn(name = "jogoid"))
	@JsonView(Views.Internal.class)
	private Set<Jogo> jogos = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "usuario", cascade=CascadeType.REMOVE, orphanRemoval = true)
	private Set<ListaPersonalizada> listaspersonalizadas = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", cascade=CascadeType.REMOVE, orphanRemoval = true)
	private Set<Score> scores = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", cascade=CascadeType.REMOVE, orphanRemoval = true)
	private Set<Gostei> gosteis = new HashSet<>();
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}
	
	
	public Set<Jogo> getJogos() {
		return jogos;
	}


	public void setJogos(Set<Jogo> jogos) {
		this.jogos = jogos;
	}


	public Set<ListaPersonalizada> getListaspersonalizadas() {
		return listaspersonalizadas;
	}


	public void setListaspersonalizadas(Set<ListaPersonalizada> listaspersonalizadas) {
		this.listaspersonalizadas = listaspersonalizadas;
	}


	public Set<Score> getScores() {
		return scores;
	}


	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getSobrenome() {
		return sobrenome;
	}


	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public Boolean getAdmin() {
		return admin;
	}


	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}


	public String getImagem() {
		return imagem;
	}


	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	
	public Set<Gostei> getGosteis() {
		return gosteis;
	}


	public void setLikes(Set<Gostei> gostei) {
		this.gosteis = gostei;
	}
	
	
}
