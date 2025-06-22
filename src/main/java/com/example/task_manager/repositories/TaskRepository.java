package com.example.task_manager.repositories;

import com.example.task_manager.enities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {


    List<Task> findAllByTitleContaining(String title);

    List<Task> findAllByUserId(Long id);
}
