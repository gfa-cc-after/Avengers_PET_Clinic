package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.*;
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
    private final JwtTokenService jwtTokenService;


    @Override
    public UpdateUserResponseDTO updateUser(String email, UpdateUserRequestDTO updateUserRequestDTO) {
        User existingUser = userRepository.findByEmail(email).orElse(null);
//        check if User exists
        if (existingUser == null) {
            return new UpdateUserResponseDTO(updateUserRequestDTO.email(), "User not found");
        }
//        check if new email is not already used by another user
        if (!existingUser.getEmail().equals(updateUserRequestDTO.email()) && userRepository.existsByEmail(updateUserRequestDTO.email())) {
            return new UpdateUserResponseDTO(updateUserRequestDTO.email(), "The email already exist");
        }

        existingUser.setEmail(updateUserRequestDTO.email());
        existingUser.setPassword(passwordEncoder.encode(updateUserRequestDTO.password()));
        userRepository.save(existingUser);

        return new UpdateUserResponseDTO(updateUserRequestDTO.email(), "Update was successful");
    }


    @Override
    public RegisterUserResponseDTO addUser(RegisterUserRequestDTO registerUserRequestDTO) {
        if (userRepository.existsByEmail(registerUserRequestDTO.email())) {
            throw new IllegalArgumentException("Email is already in use");
        }

        User newUser = new User();
        newUser.setEmail(registerUserRequestDTO.email());
        newUser.setPassword(passwordEncoder.encode(registerUserRequestDTO.password()));
        userRepository.save(newUser);

        return new RegisterUserResponseDTO();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email is not in database"));
    }


    @Override
    public DeleteUserResponseDTO deleteUser(String email) {
        userRepository.deleteByEmail(email);
        return DeleteUserResponseDTO.builder().response("User was successfully deleted").build();
    }

    @Override
    public LoginUserResponseDTO loginUser(LoginUserRequestDTO loginUserRequestDTO) {
        User user = userRepository.findByEmail(loginUserRequestDTO.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!passwordEncoder.matches(loginUserRequestDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password is not valid");
        }
        return new LoginUserResponseDTO(jwtTokenService.generateToken(user));
    }
}
