package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.*;
import com.avangers.backendapi.models.User;
import com.avangers.backendapi.repositories.UserRepository;
import jakarta.transaction.Transactional;
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
        // check if User exists
        if (existingUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        // check if new email is not already used by another user
        if (!existingUser.getEmail().equals(updateUserRequestDTO.email()) && userRepository.existsByEmail(updateUserRequestDTO.email())) {
            throw new IllegalArgumentException("The email already exist");
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

    // Transactional annotation is used to make sure that the method is executed within a transaction
    // Since the user is being deleted "during" it is logged in, we need to make sure that the operation is going thought
    // otherwise we got an error with concurrency
    // https://stackoverflow.com/questions/32269192/spring-no-entitymanager-with-actual-transaction-available-for-current-thread
    @Transactional
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

    @Override
    public FindUserResponseDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new FindUserResponseDTO(user.getId(), user.getEmail());
    }
}
