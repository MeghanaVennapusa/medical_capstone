package com.wecp.medicalequipmentandtrackingsystem.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecp.medicalequipmentandtrackingsystem.dto.OtpRequest;
import com.wecp.medicalequipmentandtrackingsystem.dto.ResetPasswordRequest;
import com.wecp.medicalequipmentandtrackingsystem.service.EmailService;
import com.wecp.medicalequipmentandtrackingsystem.service.OtpService;
import com.wecp.medicalequipmentandtrackingsystem.service.UserService;

@RestController
@RequestMapping("/api")
public class PasswordController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

  @PostMapping("/send-otp")
public ResponseEntity<Map<String, String>> sendOtp(@RequestBody OtpRequest request) {
    String otp = otpService.generateOtp(request.getEmail());
    emailService.sendOtpEmail(request.getEmail(), otp);

    Map<String, String> response = new HashMap<>();
    response.put("message", "OTP sent successfully");

    return ResponseEntity.ok(response);
}

@PostMapping("/reset-password")
public ResponseEntity<Map<String, String>> resetPassword(@RequestBody ResetPasswordRequest request) {
    try {
        userService.resetPassword(request.getEmail(), request.getOtp(), request.getNewPassword());

        Map<String, String> response = new HashMap<>();
        response.put("message", "Password reset successful");
        return ResponseEntity.ok(response);
    } catch (RuntimeException e) {
        Map<String, String> error = new HashMap<>();
        error.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

}