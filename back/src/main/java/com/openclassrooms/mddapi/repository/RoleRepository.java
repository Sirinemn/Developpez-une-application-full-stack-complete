package com.openclassrooms.mddapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.models.Role;

public interface RoleRepository  extends JpaRepository<Role, Long>{

}
