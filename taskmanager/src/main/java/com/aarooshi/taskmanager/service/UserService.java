package com.aarooshi.taskmanager.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aarooshi.taskmanager.entity.User;
import com.aarooshi.taskmanager.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    public User updateUser(Long id, User updatedUser) {
        User user = getUserById(id);

        user.setFName(updatedUser.getFName());
        user.setLName(updatedUser.getLName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setRole(updatedUser.getRole());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User uploadUserImage(
            Long id,
            MultipartFile image) {

        try {

            User user = getUserById(id);

            // Delete old image if exists
            if (user.getProfileImage() != null &&
                    !user.getProfileImage().isBlank()) {

                Path oldFilePath = Paths.get(user.getProfileImage());

                Files.deleteIfExists(oldFilePath);
            }

            // Create uploads/users folder if it doesn't exist
            String uploadDir = "uploads/users/";

            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate unique filename
            String fileName = UUID.randomUUID()
                    + "_"
                    + image.getOriginalFilename();

            Path filePath = uploadPath.resolve(fileName);

            // Save new file
            Files.copy(
                    image.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING);

            // Save path in database
            user.setProfileImage(uploadDir + fileName);

            return userRepository.save(user);

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }

}
