package com.openclassrooms.mddapi.dto;


import java.util.Set;

import com.openclassrooms.mddapi.enumeration.Topics;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicDto {
    private Long id;
	
    @NotBlank
    @Size(max = 20)
	private Topics name;
    
    Set<Long> articles;
    
    Set<Long> users;

}
