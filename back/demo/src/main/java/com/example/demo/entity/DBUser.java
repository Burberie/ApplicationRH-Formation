package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "user")
@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private Long id;
    @Column private String email;
    @Column private String password;
    @Column private String lastname;
    @Column private String firstname;
    @Column private String role;
}