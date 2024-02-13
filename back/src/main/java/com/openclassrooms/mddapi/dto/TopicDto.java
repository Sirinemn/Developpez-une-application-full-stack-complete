package com.openclassrooms.mddapi.dto;


import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.openclassrooms.mddapi.enumeration.Topics;

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
    
    List<Long> articles;
    
    List<Long> users;

}
