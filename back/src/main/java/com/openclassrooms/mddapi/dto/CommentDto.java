package com.openclassrooms.mddapi.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
	private Long id;
	
	@NotNull
    @Size(max = 250)
	private String content;	
	
	@NotNull
	private Long userId;
	
	@NotNull
	private Long articleId;

}
