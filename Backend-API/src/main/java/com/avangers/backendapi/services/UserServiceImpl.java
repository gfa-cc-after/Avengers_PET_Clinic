package com.avangers.backendapi.services;


import com.avangers.backendapi.DTOs.DeleteUserResponseDTO;
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
  public DeleteUserResponseDTO deleteUser(String email) {
        userRepository.deleteByEmail(email);
        return DeleteUserResponseDTO.builder()
                .response("User was successfully deleted")
                .build();
    }

    @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Email is not in database")
        );
    }
}