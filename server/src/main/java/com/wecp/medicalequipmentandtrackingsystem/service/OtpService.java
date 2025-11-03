package com.wecp.medicalequipmentandtrackingsystem.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    private final Map<String, String> otpStorage = new ConcurrentHashMap<>();

    public String generateOtp(String email) {
        String normalizedEmail = email.trim().toLowerCase();
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        otpStorage.put(normalizedEmail, otp);
        System.out.println("Generated OTP for " + normalizedEmail + ": " + otp);
        return otp;
    }

    public boolean validateOtp(String email, String otp) {
        String normalizedEmail = email.trim().toLowerCase();
        String storedOtp = otpStorage.get(normalizedEmail);
        System.out.println("Validating OTP for " + normalizedEmail + ": " + otp + " vs " + storedOtp);
        return otp.equals(storedOtp);
    }

    public void clearOtp(String email) {
        String normalizedEmail = email.trim().toLowerCase();
        otpStorage.remove(normalizedEmail);
        System.out.println("Cleared OTP for " + normalizedEmail);
    }
}