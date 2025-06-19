package com.example.task_manager.services.auth;


import com.example.task_manager.dto.SignUpRequest;
import com.example.task_manager.dto.UserDTO;
import com.example.task_manager.enities.User;
import com.example.task_manager.enums.UserRole;
import com.example.task_manager.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @PostConstruct
    public void createAnAdminAccount() {
        Optional<User> optionalUser = userRepository.findByUserRole(UserRole.ADMIN);
        if (optionalUser.isEmpty()) {
            User user = new User();
            user.setEmail("     admin@gmail.com");
            user.setName("admin");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setUserRole(UserRole.ADMIN);
            userRepository.save(user);
            System.out.println("Created Successfully!");
        } else {
            System.out.println("Admin account already exist!");
        }
    }

    @Override
    public UserDTO signupUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setUserRole(UserRole.EMPLOYEE);
       User createdUser = userRepository.save(user);
        return createdUser.getUserDTO();
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
