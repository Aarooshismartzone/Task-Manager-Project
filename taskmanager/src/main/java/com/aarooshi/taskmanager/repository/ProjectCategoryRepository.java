package com.aarooshi.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aarooshi.taskmanager.entity.ProjectCategory;

public interface ProjectCategoryRepository extends JpaRepository<ProjectCategory, Long> {
    
}
