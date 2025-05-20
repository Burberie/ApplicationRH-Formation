package com.mgas.formation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class DBUser {

@Id
    private Long id = 0l;
private String email;
 private String password;
private String lastname;
 private String firstname;
 private String role;

    public DBUser(String email, String password, String lastname, String firstname, String role) {
        this.id++;
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.role = role;
    }
}