package com.aarooshi.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aarooshi.taskmanager.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

/*
 * Spring Data JPA generates the implementation automatically at runtime.
 * So, we instantly get
 * 
 * userRepository.findAll();
 * userRepository.findById(1L);
 * userRepository.save(user);
 * userRepository.deleteById(1L);
 * 
 * without writing SQL.
 * 
 * Check UserController.java under controller directory
 */
