package com.aarooshi.taskmanager.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
/*
 * Why did I write @RestController?
 * 
 * @Restcontroller tells Spring Boot: This class handles HTTP requests, and the
 * return values should
 * be sent directly back to the client as the response body.
 */
public class HealthController {
    @GetMapping("/")
    public String home() {
        return "Task Management System Running Successfully";
    }

    @GetMapping("/api/health")
    public String health() {
        return "Application is healthy";
    }
}
