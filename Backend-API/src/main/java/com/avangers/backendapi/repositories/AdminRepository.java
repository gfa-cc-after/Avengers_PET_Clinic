package com.avangers.backendapi.repositories;

import com.avangers.backendapi.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
