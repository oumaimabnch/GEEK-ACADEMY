package com.gdev.geekacademybackend.repositories;

import com.gdev.geekacademybackend.models.Staff;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StaffRepository extends UserBaseRepository<Staff> {

}
