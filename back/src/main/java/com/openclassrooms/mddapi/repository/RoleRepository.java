package com.openclassrooms.mddapi.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.models.Role;

public interface RoleRepository  extends JpaRepository<Role, Long>{
	Optional<Role> findByName(String name);

}
