package com.avangers.backendapi.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "verified")
    private int verified;

    public boolean isVerified() {
        return verified == 1;
    }

    public void setVerified(boolean verified) {
        this.verified = verified ? 1 : 0;
    }

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    // orphanRemoval removes Pet from DB also once it is removed from List
    private List<Pet> pets = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
