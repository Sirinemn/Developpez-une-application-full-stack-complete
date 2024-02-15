package com.openclassrooms.mddapi.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	    private Set<Comment> comments;
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
