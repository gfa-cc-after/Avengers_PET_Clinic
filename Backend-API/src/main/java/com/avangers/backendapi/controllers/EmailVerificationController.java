package com.avangers.backendapi.controllers;

import com.avangers.backendapi.services.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class EmailVerificationController {

    private final EmailVerificationService emailVerificationService;

    @GetMapping("/verify/email")
    public ResponseEntity<String> verifyEmail(@RequestParam UUID id) {
        try {

            // Retrieve the email associated with the verification ID
            String email = emailVerificationService.getEmailForVerificationId(id);

            if (email == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification ID.");
            }

            // Use EmailVerificationService to verify the customer by email
            emailVerificationService.verifyCustomerByEmail(email);

            // If successful, return an appropriate response
            return ResponseEntity.status(HttpStatus.OK).body("Email verification successful. Please log in.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID format.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during verification.");
        }
    }
}
