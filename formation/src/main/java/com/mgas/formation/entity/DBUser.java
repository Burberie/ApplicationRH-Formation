package com.mgas.formation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mgas.formation.enumeration.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Getter
@Setter
public class DBUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @NotBlank(message = "This field is mandatory")
    @Column(name = "email")
    private String email;
    @NotBlank(message = "This field is mandatory")
    @Column(name = "password")
    private String password;
    @NotBlank(message = "This field is mandatory")
    @Column(name = "lastname")
    private String lastname;
    @NotBlank(message = "This field is mandatory")
    @Column(name = "firstname")
    private String firstname;
    @NotNull(message = "This field is mandatory")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "birthdate")
    private Date birthdate;
    @NotNull(message = "This field is mandatory")
    @Column(name = "role")
    private UserRole role;

    public DBUser() {}

    public DBUser(String email, String password, String lastname, String firstname, Date birthdate, UserRole role) {
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthdate = birthdate;
        this.role = role;
    }
}