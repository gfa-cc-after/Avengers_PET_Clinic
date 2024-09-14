package com.avangers.backendapi.models;

import java.util.Collection;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Table(name = "admins")
@NoArgsConstructor
public class Admin extends User {

}
