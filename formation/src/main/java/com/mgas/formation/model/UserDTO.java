package com.mgas.formation.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.mgas.formation.enumeration.ContractEnum;
import com.mgas.formation.enumeration.RoleEnum;
import com.mgas.formation.enumeration.ServiceEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Data
public class UserDTO {

    @Setter(AccessLevel.NONE)
    private Long id;
    @NotBlank(message = "Email must not be blank.")
    private String email;
    @NotBlank(message = "Username must not be blank.")
    private String username;
    @NotBlank(message = "Password must not be blank.")
    private String password;
    @NotBlank(message = "Last Name must not be blank.")
    private String lastname;
    @NotBlank(message = "First Name must not be blank.")
    private String firstname;
    private String register_num;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @Setter(AccessLevel.NONE)
    private Date register_date = new Date();
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private Date seniority_date;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private Date end_date;
    @JsonSetter(contentNulls = Nulls.SKIP)
    @Enumerated(EnumType.STRING)
    private Set<RoleEnum> role;
    private ServiceEnum service;
    private ContractEnum contract;
    private boolean isActive = true;

    public UserDTO(Long id, String email, String username, String password, String lastname, String firstname, String register_num, Date seniority_date, Date end_date, Set<RoleEnum> role, ServiceEnum service, ContractEnum contract) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.register_num = register_num;
        this.seniority_date = seniority_date;
        this.end_date = end_date;
        this.role = role;
        this.service = service;
        this.contract = contract;
    }
}