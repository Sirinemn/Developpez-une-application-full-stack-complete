package com.openclassrooms.mddapi.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.jwt.TokenProvider;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.services.UserService;

import jakarta.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.LoginRequest;


@RestController
@RequestMapping("/api")
public class UserController {
	private final TokenProvider tokenProvider;
	private final UserService userService;
	private final UserMapper userMapper;
	private final String AUTHORIZATION_HEADER = "Authorization";

	private final AuthenticationManager authenticationManager;

	public UserController(UserMapper userMapper,TokenProvider tokenProvider, AuthenticationManager authenticationManager,
			UserService userService) {
		this.tokenProvider = tokenProvider;
		this.userService = userService;
		this.userMapper = userMapper;
		this.authenticationManager = authenticationManager;
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
		User user = userService.findById(id);
		
		return ResponseEntity.ok(this.userMapper.toDto(user));
	}
	@PostMapping("/login")
	public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginRequest login) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				login.getEmail(), login.getPassword());

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
