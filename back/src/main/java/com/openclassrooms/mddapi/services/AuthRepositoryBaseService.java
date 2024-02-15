package com.openclassrooms.mddapi.services;

import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.RegisterRequest;
import com.openclassrooms.mddapi.repository.RoleRepository;
import com.openclassrooms.mddapi.repository.UserRepository;


@Service
public class AuthRepositoryBaseService implements AuthService{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository ;

	public AuthRepositoryBaseService(UserRepository userRepository, PasswordEncoder passwordEncoder,
			RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	public User save(RegisterRequest register) throws BadRequestException{
		if (userRepository.findByLastName(register.getLastName())== null) {
			throw new BadRequestException();
		}
		
		User user = new User();
		user.setLastName(register.getLastName());
		user.setEmail(register.getEmail());
		user.setPassword(passwordEncoder.encode(register.getPassword()));

		roleRepository.findByName("USER")
		.ifPresent(r -> user.setRoles(Collections.singletonList(r)));
		User saved=userRepository.save(user);
		return saved;
		
	}

}
