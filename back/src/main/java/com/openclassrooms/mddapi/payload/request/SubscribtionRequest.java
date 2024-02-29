package com.openclassrooms.mddapi.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SubscribtionRequest {
	@NotBlank
	  @Size(min = 3, max = 20)
	  private String userId;

	  @NotBlank
	  @Size(min = 6, max = 40)
	  private String topicId;

}
