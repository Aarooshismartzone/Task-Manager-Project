package com.aarooshi.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aarooshi.taskmanager.entity.User;
import com.aarooshi.taskmanager.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping // the url /api/users is called under GET method
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping // the (same) url /api/users is called under POST method
    public User createUser(@RequestBody User user) {
        return userRepository.save(user); //Add a user, and save the details.
    }
}

/*
 * When we visit: "http://localhost:9090/api/users",
 *
 * Spring executes:
 * userRepository.findAll();
 *
 * JpaRepository translates this into a SQL query similar to:
 * SELECT * FROM users;
 *
 * The records are fetched from PostgreSQL.
 *
 * Spring then converts the List<User> into JSON
 * and returns it to the browser/client.
 *
 * UserRepository is an interface.
 * Using Dependency Injection and Spring Data JPA,
 * Spring automatically generates an implementation
 * of UserRepository and injects it into this controller.
 */