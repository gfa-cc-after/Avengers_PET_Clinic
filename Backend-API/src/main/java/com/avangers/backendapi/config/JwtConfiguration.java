package com.avangers.backendapi.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class JwtConfiguration {
  @Value("${security.oauth2.resourceserver.jwt.secret}")
  private String secret;

  @Value("${security.oauth2.resourceserver.jwt.token.validity}")
  private long tokenValidity;
}
