package com.example.task_manager.services.employee;

import com.example.task_manager.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {

    List<TaskDTO> getTaskByUserId();

    TaskDTO updateTask(Long id,String status);
}
