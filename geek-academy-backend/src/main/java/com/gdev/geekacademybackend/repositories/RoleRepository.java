package com.gdev.geekacademybackend.repositories;

import java.util.Optional;

import com.gdev.geekacademybackend.models.Role;
import com.gdev.geekacademybackend.models.RoleName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, RoleName> {
    Optional<Role> findByName(RoleName roleName);

}
