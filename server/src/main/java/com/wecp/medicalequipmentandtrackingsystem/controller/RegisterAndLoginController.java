
package com.wecp.medicalequipmentandtrackingsystem.controller;

import com.wecp.medicalequipmentandtrackingsystem.dto.LoginRequest;
import com.wecp.medicalequipmentandtrackingsystem.dto.LoginResponse;
import com.wecp.medicalequipmentandtrackingsystem.dto.UserDTO;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.User;
import com.wecp.medicalequipmentandtrackingsystem.jwt.JwtUtil;
import com.wecp.medicalequipmentandtrackingsystem.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
public class RegisterAndLoginController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterAndLoginController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    // register user and return the registered user with status code 201 created
   
  @PostMapping("/api/user/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO userDTO) {
        logger.info("Registering user: {}", userDTO.getUsername());

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());

        User createdUser = userService.createUser(user);
        logger.info("User registered successfully: {}", user.getUsername());

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @PostMapping("/api/user/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        logger.info("Login attempt for username: {}", loginRequest.getUsername());

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
            String token = jwtUtil.generateToken(loginRequest.getUsername());
            User user = userService.getUserByUsername(loginRequest.getUsername());

            logger.info("Login successful for username: {}", loginRequest.getUsername());

            LoginResponse response = new LoginResponse(token, userDetails.getUsername(), user.getEmail(), user.getRole());
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (AuthenticationException e) {
            logger.warn("Login failed for username: {}", loginRequest.getUsername());
            logger.error("AuthenticationException: {} ", e.getMessage());

            LoginResponse response = new LoginResponse(null, null, null, null);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}