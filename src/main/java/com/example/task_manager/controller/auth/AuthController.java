package com.example.task_manager.controller.auth;


import com.example.task_manager.dto.AuthenticationRequest;
import com.example.task_manager.dto.AuthenticationResponse;
import com.example.task_manager.dto.SignUpRequest;
import com.example.task_manager.dto.UserDTO;
import com.example.task_manager.enities.User;
import com.example.task_manager.repositories.UserRepository;
import com.example.task_manager.services.auth.AuthService;
import com.example.task_manager.services.jwt.UserService;
import com.example.task_manager.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignUpRequest signUpRequest) {
        if (authService.hasUserWithEmail(signUpRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User already exist with email");


        }

        UserDTO createdUserDTO = authService.signupUser(signUpRequest);
        if (createdUserDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not created");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDTO);
    }

    @PostMapping("/signin")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }

        final UserDetails userDetails = userService.userDetailService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser =userRepository.findFirstByEmail(authenticationRequest.getEmail());
        final String jwtToken = jwtUtils.generateToken(userDetails);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(optionalUser.isPresent()){
            authenticationResponse.setJwt(jwtToken);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        }
        return  authenticationResponse;
    }

}
