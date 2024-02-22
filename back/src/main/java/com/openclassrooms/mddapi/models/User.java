package com.openclassrooms.mddapi.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

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
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Entity
@Table(name = "USERS")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
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
	  @Column(name = "name")
	  private String name;


	  @NonNull
	  @Size(max = 120)
	  private String password;

	  @CreatedDate
	  @Column(name = "created_at", updatable = false)
	  private LocalDateTime createdAt;

	  @UpdateTimestamp
	  @Column(name = "updated_at")
	  private LocalDateTime updatedAt;
	  
	  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
		@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
		private List<Role> roles;
	  
		@ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	            name = "SUBSCRIBE",
	            joinColumns = @JoinColumn( name = "user_id" ),
	            inverseJoinColumns = @JoinColumn( name = "topic_id" ) )
	    private Set<Topic> topics ;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getName() {
			return name;
		}

		public void setName(String lastName) {
			this.name = lastName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}

		public List<Role> getRoles() {
			return roles;
		}

		public void setRoles(List<Role> roles) {
			this.roles = roles;
		}

		public Set<Topic> getTopics() {
			return topics;
		}

		public void setTopics(Set<Topic> topics) {
			this.topics = topics;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		
		
	 
	  
}
