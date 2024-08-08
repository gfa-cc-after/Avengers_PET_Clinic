package com.avangers.backendapi.services;

import com.avangers.backendapi.DTOs.RegisterUserRequestDTO;
import com.avangers.backendapi.DTOs.RegisterUserResponseDTO;
import com.avangers.backendapi.DTOs.UpdateUserResponseDTO;
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
    public UpdateUserResponseDTO updateUser(Long userId, RegisterUserRequestDTO registerUserRequestDTO) {
        User existingUser = userRepository.findById(userId).orElse(null);
//        check if User exists
        if (existingUser == null) {
            return new UpdateUserResponseDTO(registerUserRequestDTO.email(),"User not found");
        }
//        check if new email is not already used by another user
        if (!existingUser.getEmail().equals(registerUserRequestDTO.email()) && userRepository.existsByEmail(registerUserRequestDTO.email())) {
            return new UpdateUserResponseDTO(registerUserRequestDTO.email(),"The email already exist");
        }

        existingUser.setEmail(registerUserRequestDTO.email());
        existingUser.setPassword(passwordEncoder.encode(registerUserRequestDTO.password()));
        userRepository.save(existingUser);

        return new UpdateUserResponseDTO(registerUserRequestDTO.email(), "Update was successful");
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Email is not in database")
        );
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

}
