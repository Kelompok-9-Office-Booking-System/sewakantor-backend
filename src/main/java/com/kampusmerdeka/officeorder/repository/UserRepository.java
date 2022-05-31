package com.kampusmerdeka.officeorder.repository;

import com.kampusmerdeka.officeorder.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}