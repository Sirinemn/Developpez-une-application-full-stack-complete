package com.openclassrooms.mddapi.models;

import java.util.Set;

import com.openclassrooms.mddapi.enumeration.Topics;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "SUBSCRIBE",
            joinColumns = @JoinColumn( name = "topic_id" ),
            inverseJoinColumns = @JoinColumn( name = "user_id" ) )
    private Set<User> users ;
	
	@OneToMany(
			mappedBy = "topic", 
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
    private Set<Article> articles;

}
