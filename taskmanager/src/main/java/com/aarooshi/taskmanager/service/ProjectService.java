package com.aarooshi.taskmanager.service;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aarooshi.taskmanager.entity.Project;
import com.aarooshi.taskmanager.repository.ProjectRepository;

@Service
public class ProjectService {

    // @Autowired
    // private ProjectRepository projectRepository;

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project Not Found"));
    }

    public Project updateProject(Long id, Project updatedProject) {
        Project project = getProjectById(id);

        project.setProjectName(updatedProject.getProjectName());
        project.setDescription(updatedProject.getDescription());

        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

}
