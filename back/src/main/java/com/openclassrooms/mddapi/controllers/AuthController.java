package com.openclassrooms.mddapi.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.jwt.TokenProvider;
import com.openclassrooms.mddapi.payload.request.RegisterRequest;
import com.openclassrooms.mddapi.services.AuthRepositoryBaseService;

@RestController
@RequestMapping("/api")
public class AuthController {
	private AuthRepositoryBaseService authService;
	private TokenProvider tokenProvider;
	private AuthenticationManager authenticationManager;
	private final String AUTHORIZATION_HEADER = "Authorization";

	public AuthController(AuthRepositoryBaseService authService, TokenProvider tokenProvider,
			AuthenticationManager authenticationManager) {
		this.authService = authService;
		this.tokenProvider = tokenProvider;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/register")
	public ResponseEntity<JWTToken> register(@RequestBody RegisterRequest register)throws BadRequestException {
		authService.save(register);
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				register.getEmail(), register.getPassword());

		Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.createToken(authentication);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(AUTHORIZATION_HEADER, "Bearer " + jwt);
		return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
	}
	
	static class JWTToken {

		private String idToken;

		public JWTToken(String idToken) {
			this.idToken = idToken;
		}

		@JsonProperty("token")
		String getIdToken() {
			return idToken;
		}

		void setIdToken(String idToken) {
			this.idToken = idToken;
		}
	}

}
