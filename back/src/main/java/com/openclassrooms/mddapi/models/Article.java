package com.openclassrooms.mddapi.models;

import java.time.LocalDateTime;

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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
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
	    @Size(max = 2500)
	    private String content;
	    
	    @CreatedDate
		@Column(name = "created_at", updatable = false)
		private LocalDateTime createdAt;

		@UpdateTimestamp
		@Column(name = "updated_at")
		private LocalDateTime updatedAt;
		
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

		public String getTitre() {
			return titre;
		}

		public void setTitre(String titre) {
			this.titre = titre;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Topic getTopic() {
			return topic;
		}

		public void setTopic(Topic topic) {
			this.topic = topic;
		}

		public Long getId() {
			return id;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
		
		

}
