package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.openclassrooms.mddapi.models.Role;
import com.openclassrooms.mddapi.models.Topic;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	  private Long id;

	  @NonNull
	  @Size(max = 50)
	  @Email
	  private String email;

	  @NonNull
	  @Size(max = 20)
	  private String lastName;
	  
	  Set<Topic> topics;
	  
	  List<Role> roles;

	  private LocalDateTime createdAt;

	  private LocalDateTime updatedAt;

}
