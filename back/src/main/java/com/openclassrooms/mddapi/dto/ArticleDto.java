package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
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
public class ArticleDto {
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String titre;

    @NotNull
    @Size(max = 2500)
    private String content;
    
	private LocalDateTime createdAt;
	
    @NotNull
    private Long topicId;
    
    private String topicName;
    
    @NotNull
    private Long userId;
    private String userName;

}
