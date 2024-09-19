package com.avangers.backendapi.services;

import com.avangers.backendapi.models.Customer;
import com.avangers.backendapi.models.EmailVerification;
import com.avangers.backendapi.repositories.CustomerRepository;
import com.avangers.backendapi.repositories.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final EmailVerificationRepository emailVerificationRepository;
    private final CustomerRepository customerRepository;

    // Generates a verification ID for an email
    public String generateVerification(String email) {
        if (!emailVerificationRepository.existsByEmail(email)) {
            EmailVerification emailVerification = new EmailVerification();
            emailVerification.setEmail(email);
            emailVerification = emailVerificationRepository.save(emailVerification);
            return emailVerification.getVerificationId().toString();
        }
        return getVerificationIdByEmail(email);
    }

    // Provides the verification ID for an email
    public String getVerificationIdByEmail(String email) {
        EmailVerification emailVerification = emailVerificationRepository.findByEmail(email);
        if (emailVerification != null) {
            return emailVerification.getVerificationId().toString();
        }
        return null;
    }

    // Provides the email for a verification ID
    public String getEmailForVerificationId(UUID verificationId) {
        Optional<EmailVerification> emailVerification = emailVerificationRepository.findById(verificationId);
        return emailVerification.map(EmailVerification::getEmail).orElse(null);
    }

    // Verifies the customer by email
    public void verifyCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found"));

        customer.setVerified(true);  // Mark the customer as verified
        customerRepository.save(customer);  // Save the updated customer
    }
}
