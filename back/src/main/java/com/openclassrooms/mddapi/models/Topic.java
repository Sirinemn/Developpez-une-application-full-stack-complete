package com.openclassrooms.mddapi.models;

import com.openclassrooms.mddapi.enumeration.Topics;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TOPICS")
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="topic_id")
	private Long id;
	
	private Topics name;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Topics getName() {
		return name;
	}

	public void setName(Topics name) {
		this.name = name;
	}
}
