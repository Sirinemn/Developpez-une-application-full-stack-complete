package com.openclassrooms.mddapi.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.openclassrooms.mddapi.enumeration.Topics;

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
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "SUBSCRIBE",
            joinColumns = @JoinColumn( name = "topic_id" ),
            inverseJoinColumns = @JoinColumn( name = "user_id" ) )
    private List<User> users;
	
	@OneToMany(
			mappedBy = "topic", 
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
    List<Article> articles = new ArrayList<>();

}
