package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

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
    private Date date;

    @NotNull
    @Size(max = 2500)
    private String content;
    
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
	
    Set<Long> comments;
    @NotNull
    private Long topicId;
    
    @NotNull
    private Long userId;

}
