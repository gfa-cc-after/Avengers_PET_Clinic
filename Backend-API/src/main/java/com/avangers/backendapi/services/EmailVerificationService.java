package com.avangers.backendapi.services;

import com.avangers.backendapi.models.EmailVerification;
import com.avangers.backendapi.models.User;
import com.avangers.backendapi.repositories.EmailVerificationRepository;
import com.avangers.backendapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailVerificationService {

    private final EmailVerificationRepository emailVerificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public EmailVerificationService(EmailVerificationRepository emailVerificationRepository, UserRepository userRepository) {
        this.emailVerificationRepository = emailVerificationRepository;
        this.userRepository = userRepository;
    }

    //Generates a verification ID for an email
    public String generateVerification(String email) {
        if (!emailVerificationRepository.existsByEmail(email)) {
            EmailVerification emailVerification = new EmailVerification();
            emailVerification = emailVerificationRepository.save(emailVerification);
            return emailVerification.getVerificationId();
        }
        return getVerificationIdByEmail(email);
    }

    //Provides the verification ID for an email
    public String getVerificationIdByEmail(String email) {
        EmailVerification emailVerification = emailVerificationRepository.findByEmail(email);
        if (emailVerification != null) {
            return emailVerification.getVerificationId();
        }
        return null;
    }

    //Provides the email for a verification ID
    public String getEmailForVerificationId(String verificationId) {
        Optional<EmailVerification> emailVerification =
                emailVerificationRepository.findById(verificationId);
        if (emailVerification.isPresent()) {
            return emailVerification.get().getEmail();
        }
        return null;
    }
    // Verifies the user by their email
    public void verifyUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setVerified(true);
        userRepository.save(user); // Save the updated user entity
    }
}
