package com.avangers.backendapi.repositories;

import com.avangers.backendapi.models.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerification, UUID> {

    EmailVerification findByEmail(String email);
    boolean existsByEmail(String email);
}
