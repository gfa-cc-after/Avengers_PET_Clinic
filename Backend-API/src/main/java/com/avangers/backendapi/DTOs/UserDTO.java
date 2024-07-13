package com.avangers.backendapi.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
   private String email;
   private String password;
}
