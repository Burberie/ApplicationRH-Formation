package com.mgas.formation.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgas.formation.enumeration.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

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

    /*public boolean hasEmptyField() {
        return this.email.isEmpty() || this.password.isEmpty() || this.lastname.isEmpty() || this.firstname.isEmpty() || this.birthdate == null || this.role.toString().isEmpty();
    }

    public String EmptyFields() {
        boolean coma = false;
        String s = "";
        if (this.email.isEmpty()) {coma = true; s = s + "Email";}
        if (this.password.isEmpty()) {if (coma) {s = s + ", ";} coma = true; s = s + "Password";}
        if (this.lastname.isEmpty()) {if (coma) {s = s + ", ";} coma = true; s = s + "Lastname";}
        if (this.firstname.isEmpty()) {if (coma) {s = s + ", ";} coma = true; s = s + "Firstname";}
        if (this.birthdate == null) {if (coma) {s = s + ", ";} coma = true; s = s + "Birthdate";}
        if (this.role.name().isEmpty()) {if (coma) {s = s + ", ";} s = s + "Role";}
        return s;
    }*/
}