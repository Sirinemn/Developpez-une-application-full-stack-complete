package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

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

	  @NonNull
	  @Size(max = 20)
	  private String firstName;

	  @NonNull
	  @Size(max = 120)
	  private String password;
	  
	  List<Long> topics;
	  
	  List<Long> comments;

	  List<Long> articles;


	  private LocalDateTime createdAt;

	  private LocalDateTime updatedAt;

}
