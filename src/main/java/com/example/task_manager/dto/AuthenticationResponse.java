package com.example.task_manager.dto;

import com.example.task_manager.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;
    private Long userId;
    private UserRole userRole;

}
