package com.openclassrooms.mddapi.models;




import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "COMMENTS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="comment_id")
	private Long id;
	
	@NotNull
    @Size(max = 250)
	private String content;	
	
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
	@JoinColumn(name="article_id")
	private Article article;

}
