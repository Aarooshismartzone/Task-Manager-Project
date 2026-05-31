package com.aarooshi.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aarooshi.taskmanager.entity.User;
import com.aarooshi.taskmanager.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

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
        return userRepository.save(user); // Add a user, and save the details.
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not found"));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        user.setFName(updatedUser.getFName());
        user.setLName(updatedUser.getLName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setRole(updatedUser.getRole());

        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);

        return "User Deleted Successfully";
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