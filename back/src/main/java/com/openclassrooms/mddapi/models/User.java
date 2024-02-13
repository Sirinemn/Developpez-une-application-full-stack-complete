package com.openclassrooms.mddapi.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;


import lombok.Data;
import lombok.NonNull;
@Entity
@Data
@Table(name = "USERS")
public class User {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name="user_id")
	  private Long id;

	  @NonNull
	  @Size(max = 50)
	  @Email
	  private String email;

	  @NonNull
	  @Size(max = 20)
	  @Column(name = "last_name")
	  private String lastName;

	  @NonNull
	  @Size(max = 20)
	  @Column(name = "first_name")
	  private String firstName;

	  @NonNull
	  @Size(max = 120)
	  private String password;

	  @CreatedDate
	  @Column(name = "created_at", updatable = false)
	  private LocalDateTime createdAt;

	  @UpdateTimestamp
	  @Column(name = "updated_at")
	  private LocalDateTime updatedAt;
	  
	  @ManyToMany(
				mappedBy = "users",
						cascade = { 
								CascadeType.PERSIST, 
								CascadeType.MERGE 
								}
				)
	  private List<Topic> Topics = new ArrayList<>();
	  
	  @OneToMany(
				mappedBy = "user", 
				cascade = CascadeType.ALL,
				orphanRemoval = true
				)
	  List<Comment> comments = new ArrayList<>();
	  
	  @OneToMany(
				mappedBy = "user", 
				cascade = CascadeType.ALL,
				orphanRemoval = true
				)
	  List<Article> articles = new ArrayList<>();
}
