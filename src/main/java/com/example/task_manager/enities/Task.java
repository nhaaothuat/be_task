package com.example.task_manager.enities;

import com.example.task_manager.dto.TaskDTO;
import com.example.task_manager.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;


    private Date dueDate;

    private String priority;

    private TaskStatus taskStatus;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;


    public TaskDTO getTaskDTO(){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(id);
        taskDTO.setTitle(title);
        taskDTO.setDescription(description);
        taskDTO.setEmployeeName(user.getName());
        taskDTO.setDueDate(dueDate);
        taskDTO.setEmployeeId(user.getId());
        taskDTO.setTaskStatus(taskStatus);
        taskDTO.setPriority(priority);
        return taskDTO;
    }

}
