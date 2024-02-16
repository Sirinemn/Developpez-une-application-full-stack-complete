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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Entity
@Data
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
	  @Column(name = "last_name")
	  private String lastName;


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
		
	  
	  
}
