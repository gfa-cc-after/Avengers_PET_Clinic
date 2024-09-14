package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.*;
import com.avangers.backendapi.models.Vet;
import com.avangers.backendapi.repositories.VetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return vetRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email is not in database"));

    }

    @Override
    public RegisterUserResponseDTO addVet(RegisterUserRequestDTO registerUserRequestDTO) {
        if (vetRepository.existsByEmail(registerUserRequestDTO.email())) {
            throw new IllegalArgumentException("Email is already in use");
        }

        Vet newVet = Vet.builder()
                .email(registerUserRequestDTO.email())
                .password(passwordEncoder.encode(registerUserRequestDTO.password()))
                .build();
        vetRepository.save(newVet);
        return new RegisterUserResponseDTO();
    }

    @Override
    public DeleteUserResponseDTO deleteVet(String email) {
        vetRepository.deleteByEmail(email);
        return DeleteUserResponseDTO.builder().response("User was successfully deleted").build();
    }

    @Override
    public UpdateUserResponseDTO updateVet(String email, UpdateUserRequestDTO updateUserRequestDTO) {
        Vet existingVet = vetRepository.findByEmail(email).orElse(null);
        // check if User exists
        if (existingVet == null) {
            throw new UsernameNotFoundException("User not found");
        }
        // check if new email is not already used by another user
        if (!existingVet.getEmail().equals(updateUserRequestDTO.email())
                && vetRepository.existsByEmail(updateUserRequestDTO.email())) {
            throw new IllegalArgumentException("The email already exist");
        }

        existingVet.setEmail(updateUserRequestDTO.email());
        existingVet.setPassword(passwordEncoder.encode(updateUserRequestDTO.password()));
        vetRepository.save(existingVet);

        return new UpdateUserResponseDTO(updateUserRequestDTO.email(), "Update was successful");
    }

    @Override
    public LoginUserResponseDTO loginVet(LoginUserRequestDTO loginUserRequestDTO) {
        Vet vet = vetRepository.findByEmail(loginUserRequestDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!passwordEncoder.matches(loginUserRequestDTO.getPassword(), vet.getPassword())) {
            throw new RuntimeException("Password is not valid");
        }
        return new LoginUserResponseDTO(jwtTokenService.generateToken(vet));
    }

    @Override
    public FindUserResponseDTO findVetByEmail(String email) {
        Vet vet = vetRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new FindUserResponseDTO(vet.getId(), vet.getEmail());
    }
}
