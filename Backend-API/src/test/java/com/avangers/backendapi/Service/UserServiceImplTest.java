package com.avangers.backendapi.Service;

import com.avangers.backendapi.DTOs.RegisterUserDTO;
import com.avangers.backendapi.repositories.UserRepository;
import com.avangers.backendapi.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Should return 'User not found' if user does not exist")
    @Test
    void shouldReturnUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        String result = userServiceImpl.updateUser(1L, new RegisterUserDTO("newemail@email.com", "NewPassword123"));

        assertEquals("User not found", result);
    }
}
