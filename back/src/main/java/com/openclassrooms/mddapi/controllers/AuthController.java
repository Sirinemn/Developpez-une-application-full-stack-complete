package com.openclassrooms.mddapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.jwt.TokenProvider;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.RegisterRequest;
import com.openclassrooms.mddapi.payload.response.JwtResponse;
import com.openclassrooms.mddapi.services.AuthRepositoryBaseService;
import com.openclassrooms.mddapi.services.UserService;

import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AuthController {
	private AuthRepositoryBaseService authService;
	private TokenProvider tokenProvider;
	private AuthenticationManager authenticationManager;
	private UserService userService;

	public AuthController(AuthRepositoryBaseService authService, TokenProvider tokenProvider,
			AuthenticationManager authenticationManager, UserService userService) {
		this.authService = authService;
		this.tokenProvider = tokenProvider;
		this.authenticationManager = authenticationManager;
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest register)throws BadRequestException {
		authService.save(register);
		 return ResponseEntity.ok().build();
	}
	@PostMapping("/login")
	public ResponseEntity<?> authorize(@Valid @RequestBody LoginRequest login) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				login.getEmail(), login.getPassword());

		Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.createToken(authentication);
		User user = this.userService.findByEmail(login.getEmail());
        return ResponseEntity.ok(new JwtResponse(jwt,
        		user.getId(),
        		user.getName()
                ));
	}
}
