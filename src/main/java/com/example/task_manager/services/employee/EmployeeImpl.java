package com.example.task_manager.services.employee;

import com.example.task_manager.dto.TaskDTO;
import com.example.task_manager.enities.Task;
import com.example.task_manager.enities.User;
import com.example.task_manager.enums.TaskStatus;
import com.example.task_manager.repositories.TaskRepository;
import com.example.task_manager.utils.JwtUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeImpl implements EmployeeService {
    private final TaskRepository taskRepository;

    private final JwtUtils jwtUtils;


    @Override
    public List<TaskDTO> getTaskByUserId() {
        User user = jwtUtils.getLoggedInUser();
        if (user != null) {
            return taskRepository.findAllByUserId(user.getId())
                    .stream()
                    .sorted(Comparator.comparing(Task::getDueDate).reversed())
                    .map(Task::getTaskDTO)
                    .collect(Collectors.toList());
        }
        throw new EntityNotFoundException("User not found");
    }

    @Override
    public TaskDTO updateTask(Long id, String status) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task existTask = optionalTask.get();
            existTask.setTaskStatus(mapStringTaskStatus(status));
            return taskRepository.save(existTask).getTaskDTO();
        }
        throw new EntityNotFoundException("Task not found");
    }

    private TaskStatus mapStringTaskStatus(String status){
        return switch (status){
            case "PENDING" -> TaskStatus.PENDING;
            case "INPROGRESS" -> TaskStatus.INPROGRESS;
            case "COMPLETED" -> TaskStatus.COMPLETED;
            case "DEFERRED" -> TaskStatus.DEFERED;
            default -> TaskStatus.CANCELLED;
        };
    }
}
