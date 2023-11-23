package com.gdev.geekacademybackend.repositories;

import java.util.Optional;

import com.gdev.geekacademybackend.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserBaseRepository<T extends User> extends JpaRepository<T, Long> {

    public T findByEmail(String email);

    Optional<T>findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
