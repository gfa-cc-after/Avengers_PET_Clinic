package com.avangers.backendapi.listener;

import com.avangers.backendapi.event.UserRegistrationEvent;
import com.avangers.backendapi.DTOs.RegisterUserResponseDTO;
import com.avangers.backendapi.services.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class EmailVerificationListener implements ApplicationListener<UserRegistrationEvent> {

    private final JavaMailSender mailSender;
    private final EmailVerificationService emailVerificationService;

    @Autowired
    public EmailVerificationListener(JavaMailSender mailSender, EmailVerificationService emailVerificationService) {
        this.mailSender = mailSender;
        this.emailVerificationService = emailVerificationService;
    }

    @Override
    public void onApplicationEvent(UserRegistrationEvent userRegistrationEvent) {

        RegisterUserResponseDTO userResponseDTO = userRegistrationEvent.getUserResponseDTO();

        // Get the email and generate a verification ID
        String email = userResponseDTO.email();
        String verificationId = emailVerificationService.generateVerification(email);

        // Send the email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Avengers Pet Clinic Account Verification");
        message.setText(getText(userResponseDTO, verificationId));
        message.setFrom("petclinic.avengers@gmail.com");
        message.setTo(email);
        mailSender.send(message);
    }

    // getText method to send verification link
    private String getText(RegisterUserResponseDTO userResponseDTO, String verificationId) {
        String encodedVerificationId = new String(Base64.getEncoder().encode(verificationId.getBytes()));
        StringBuffer buffer = new StringBuffer();
        buffer.append("Dear ").append(userResponseDTO.email()).append(",").append(System.lineSeparator()).append(System.lineSeparator());
        buffer.append("Your account has been successfully created in the Avengers Pet Clinic application. ");

        buffer.append("Activate your account by clicking the following link: http://localhost:8080/verify/email?id=").append(encodedVerificationId);
        buffer.append(System.lineSeparator()).append(System.lineSeparator());
        buffer.append("Regards,").append(System.lineSeparator()).append("Avengers Pet Clinic Team");
        return buffer.toString();
    }
}

