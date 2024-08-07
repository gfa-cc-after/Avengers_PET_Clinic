package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.RegisterUserDTO;
import com.avangers.backendapi.models.User;
import com.avangers.backendapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public UserRegistrationResponse addUser(RegisterUserDTO registerUserDTO) {
        if (userRepository.existsByEmail(registerUserDTO.email())) {
            return new UserRegistrationResponse("The email already exists", false);
        }

        User newUser = new User();
        newUser.setEmail(registerUserDTO.email());
        newUser.setPassword(passwordEncoder.encode(registerUserDTO.password()));
        userRepository.save(newUser);

        return new UserRegistrationResponse("Registration was successful", true);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Email is not in database")
        );
    }
}
