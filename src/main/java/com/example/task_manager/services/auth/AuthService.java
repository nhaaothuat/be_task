package com.example.task_manager.services.auth;

import com.example.task_manager.dto.SignUpRequest;
import com.example.task_manager.dto.UserDTO;

public interface AuthService {

    UserDTO signupUser(SignUpRequest signUpRequest);

    boolean hasUserWithEmail(String email);
}
