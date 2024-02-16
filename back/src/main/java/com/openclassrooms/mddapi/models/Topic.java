package com.openclassrooms.mddapi.models;

import com.openclassrooms.mddapi.enumeration.Topics;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "TOPICS")
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="topic_id")
	private Long id;
	
	private Topics name;
	

}
