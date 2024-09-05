package com.avangers.backendapi.repositories;

import com.avangers.backendapi.models.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerification, String> {

    EmailVerification findByEmail(String email);
    boolean existsByEmail(String email);
}
