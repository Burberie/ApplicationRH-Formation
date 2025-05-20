package com.example.demo.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "user")
@Entity
public class DBUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private Long id = 0l;
    @Column private String email;
    @Column private String password;
    @Column private String lastname;
    @Column private String firstname;
    @Column private String role;

    public DBUser(String email, String password, String lastname, String firstname, String role) {
        this.id++;
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.role = role;
    }
}

