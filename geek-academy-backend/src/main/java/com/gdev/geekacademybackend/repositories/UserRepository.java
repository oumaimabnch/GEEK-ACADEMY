package com.gdev.geekacademybackend.repositories;

import com.gdev.geekacademybackend.models.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends UserBaseRepository<User> {

}
