package com.avangers.backendapi.repositories;

import com.avangers.backendapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
     Optional<User> findByUsername(String username);
}
