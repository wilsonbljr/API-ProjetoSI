package com.cdj.api.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="review")
public class Review implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	@JsonView(Views.External.class)
	private long id;
	
	@JsonView(Views.External.class)
	private String review;
	
	@JsonIgnore
	@OneToOne(mappedBy = "review")
	private Score score;
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getReview() {
		return review;
	}


	public void setReview(String review) {
		this.review = review;
	}


	public Score getScore() {
		return score;
	}


	public void setScore(Score score) {
		this.score = score;
	}

	
}
