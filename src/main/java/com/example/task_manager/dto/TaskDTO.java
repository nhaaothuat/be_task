package com.example.task_manager.dto;

import com.example.task_manager.enums.TaskStatus;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
public class TaskDTO {
  private Long id;
  private String title;
  private String description;

  private Date dueDate;
  private String priority;
  private TaskStatus taskStatus;
  private Long employeeId;
  private String employeeName;
}
