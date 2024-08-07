package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.RegisterUserRequestDTO;
import com.avangers.backendapi.DTOs.RegisterUserResponseDTO;
import com.avangers.backendapi.models.User;
import com.avangers.backendapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterUserResponseDTO registerUser(RegisterUserRequestDTO registerUserRequestDTO) {
        userRepository.findByEmail(registerUserRequestDTO.email()).orElseThrow(
                () -> new UsernameNotFoundException("Email already exists")
        );

        User newUser = new User();
        newUser.setEmail(registerUserRequestDTO.email());
        newUser.setPassword(passwordEncoder.encode(registerUserRequestDTO.password()));
        userRepository.save(newUser);

        return RegisterUserResponseDTO.builder()
                .response("Registration was successfully")
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Email is not in database")
        );
    }
}
