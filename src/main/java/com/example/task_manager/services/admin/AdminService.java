package com.example.task_manager.services.admin;

import com.example.task_manager.dto.UserDTO;

import java.util.List;

public interface AdminService {

    List<UserDTO> getUsers();

}
