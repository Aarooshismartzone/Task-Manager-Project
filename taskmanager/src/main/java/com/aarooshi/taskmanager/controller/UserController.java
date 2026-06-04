package com.aarooshi.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aarooshi.taskmanager.entity.User;
//import com.aarooshi.taskmanager.repository.UserRepository;
import com.aarooshi.taskmanager.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "*") // temporarily give cors access to any site
@RestController
@RequestMapping("/api/users")
public class UserController {
    // @Autowired

    /*
     * Autowired is commented out because constructor injection is used
     * Using constructor injection instead of field injection (@Autowired).
     * The controller now depends on UserService rather than UserRepository.
     */

    // private UserRepository userRepository;
    private final UserService userService; // MOVING TO SERVICE PAGE

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping // the url /api/users is called under GET method
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping // the (same) url /api/users is called under POST method
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {

        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

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