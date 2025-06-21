package com.example.task_manager.services.admin;

import com.example.task_manager.dto.TaskDTO;
import com.example.task_manager.dto.UserDTO;

import java.util.List;

public interface AdminService {

    List<UserDTO> getUsers();

    TaskDTO createTask(TaskDTO taskDTO);

    List<TaskDTO> getTask();

    void deleteTask(Long id);

    TaskDTO getTaskById(Long id);

    TaskDTO updateTask(Long id, TaskDTO taskDTO);

    List<TaskDTO> searchTaskByTitle(String title);
}
