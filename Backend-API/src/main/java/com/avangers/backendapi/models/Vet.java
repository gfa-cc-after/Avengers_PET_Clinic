package com.avangers.backendapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Table(name = "vets")
@NoArgsConstructor
public class Vet extends User {
}
