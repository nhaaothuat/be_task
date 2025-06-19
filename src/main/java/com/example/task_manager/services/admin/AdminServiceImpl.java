package com.example.task_manager.services.admin;


import com.example.task_manager.dto.UserDTO;
import com.example.task_manager.enities.User;
import com.example.task_manager.enums.UserRole;
import com.example.task_manager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream()
                .filter(user -> user.getUserRole() == UserRole.EMPLOYEE)
                .map(User::getUserDTO)
                .collect(Collectors.toList());
    }
}
