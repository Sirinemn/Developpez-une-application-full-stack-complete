package com.openclassrooms.mddapi.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;


import lombok.Data;

@Entity
@Data
@Table(name = "ARTICLES")
public class Article {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="article_id")
	    private Long id;

	    @NotBlank
	    @Size(max = 50)
	    @Column(name="title")
	    private String titre;

	    @NotNull
	    private Date date;

	    @NotNull
	    @Size(max = 2500)
	    private String content;
	    
	    @CreatedDate
		@Column(name = "created_at", updatable = false)
		private LocalDateTime createdAt;

		@UpdateTimestamp
		@Column(name = "updated_at")
		private LocalDateTime updatedAt;
		
		@OneToMany(
				mappedBy = "article", 
				cascade = CascadeType.ALL,
				orphanRemoval = true
				)
	    List<Comment> comments = new ArrayList<>();
		
		@ManyToOne(
				cascade = { 
						CascadeType.PERSIST, 
						CascadeType.MERGE 
						}
				)
		@JoinColumn(name="user_id")
		private User user;
		
		@ManyToOne(
				cascade = { 
						CascadeType.PERSIST, 
						CascadeType.MERGE 
						}
				)
		@JoinColumn(name="topic_id")
		private Topic topic;
		
		

}
