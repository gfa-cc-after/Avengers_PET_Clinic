package com.avangers.backendapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "vets")
public class Vet extends User{
}
