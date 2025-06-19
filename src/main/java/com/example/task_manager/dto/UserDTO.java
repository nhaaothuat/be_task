package com.example.task_manager.dto;

import com.example.task_manager.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String email;

    private String password;
    private UserRole userRole;
}
