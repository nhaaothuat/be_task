package com.example.task_manager.repositories;

import com.example.task_manager.enities.User;
import com.example.task_manager.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmail(String username);

    Optional<User> findByUserRole(UserRole userRole);
}
