package com.avangers.backendapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Table(name = "vets")
public class Vet extends User {
}
