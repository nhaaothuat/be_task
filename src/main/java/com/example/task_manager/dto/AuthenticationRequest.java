package com.example.task_manager.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private final String email;
    private final String password;

}
