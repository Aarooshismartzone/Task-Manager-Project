package com.aarooshi.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aarooshi.taskmanager.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
}
