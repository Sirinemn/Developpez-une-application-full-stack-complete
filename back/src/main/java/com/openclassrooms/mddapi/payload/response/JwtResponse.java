package com.openclassrooms.mddapi.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
	  private String token;
	  private String type = "Bearer";
	  private Long id;
	  private String lastName;

	  public JwtResponse(String accessToken, Long id, String lastName) {
	    this.token = accessToken;
	    this.id = id;
	    this.lastName = lastName;
	  }

}
