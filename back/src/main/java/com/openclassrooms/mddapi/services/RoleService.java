package com.openclassrooms.mddapi.services;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Role;
import com.openclassrooms.mddapi.repository.RoleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleService {
	
	private final RoleRepository roleRepository;
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository= roleRepository;
	}
	
	public Role findById(Long id) {
		return roleRepository.findById(id).orElse(null);
	}

}
