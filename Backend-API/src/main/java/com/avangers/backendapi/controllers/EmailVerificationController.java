package com.avangers.backendapi.controllers;

import com.avangers.backendapi.DTOs.FindUserResponseDTO;
import com.avangers.backendapi.models.User;
import com.avangers.backendapi.services.EmailVerificationService;
import com.avangers.backendapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class EmailVerificationController {

    @Autowired
    private EmailVerificationService emailVerificationService;

    @Autowired
    private UserService userService;

    @GetMapping("/verify/email")
    public ResponseEntity<String> verifyEmail(@RequestParam String id) {
        try {


            byte[] actualId = Base64.getDecoder().decode(id.getBytes());
            String email = emailVerificationService.getEmailForVerificationId(new String(actualId));

            if (email != null) {
                FindUserResponseDTO findUserResponseDTO = userService.findUserByEmail(email);
                if (findUserResponseDTO != null) {
                    User user = userService.findById(findUserResponseDTO.getId());
                    if (user != null) {
                        user.setVerified(true);
                        userService.saveUser(user);
                        return ResponseEntity.status(HttpStatus.OK).body("Email verification successful. Please log in.");
                }
            }
        }
    } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID format.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during verification.");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email verification failed. Invalid ID.");
    }
}

