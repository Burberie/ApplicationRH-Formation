package com.mgas.formation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.mgas.formation.enumeration.Contract_ENUM;
import com.mgas.formation.enumeration.Role_ENUM;
import com.mgas.formation.enumeration.Service_ENUM;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
public class DBUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @NotBlank(message = "Email must not be blank.")
    @Column(name = "email")
    private String email;
    @NotBlank(message = "Username must not be blank.")
    @Column(name = "username")
    private String username;
    @NotBlank(message = "Password must not be blank.")
    @Column(name = "password")
    private String password;
    /*@NotBlank(message = "Last Name must not be blank.")
    @Column(name = "lastname")
    private String lastname;
    @NotBlank(message = "First Name must not be blank.")
    @Column(name = "firstname")
    private String firstname;*/
    /*@NotBlank(message = "Register Number must not be blank.")
    @Column(name = "register_num")
    private String register_num;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "register_date")
    private Date register_date;
    @NotNull(message = "Seniority Date must not be null")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "seniority_date")
    private Date seniority_date;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "end_date")
    private Date end_date;
    @JsonSetter(contentNulls = Nulls.SKIP)*/
    @NotEmpty(message = "Role only accept list containing 0:'SYSTEM', 1:'ADMIN', 2:'TRAINER', 3:'MANAGER' or 4:'COLLABORATOR'.")
    @Column(name = "role")
    private String role;
    /*private List<Role_ENUM> role;
    @NotNull(message = "Service only accept 0:'HR_SERVICE', 1:'CS_SERVICE', 2:'LEGAL_REGULATORY', 3:'INTERNAL_CONTROL', 4:'GENERAL_RESOURCES', 5:'AF_MANAGEMENT', 6:'OP_MANAGEMENT', 7:'MD_MANAGEMENT' and 8:'JRPD_MANAGEMENT'.")
    @Column(name = "service")
    private Service_ENUM service;
    @NotNull(message = "Contract only accept 0:'PM_CONTRACT', 1:'FT_CONTRACT', 2:'APPRENTICESHIP', 3:'INTERNSHIP'")
    @Column(name = "contract")
    private Contract_ENUM contract;
    @Column(name = "isActive")
    private boolean isActive;*/


    public DBUser() {}

    /*public DBUser(String email, String password, String lastname, String firstname, String username, String register_num, Date seniority_date, Date end_date, List<Role_ENUM> role, Service_ENUM service, Contract_ENUM contract) {
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.username = username;
        this.register_num = register_num;
        this.seniority_date = seniority_date;
        this.end_date = end_date;
        this.role = role;
        this.service = service;
        this.contract = contract;
    }*/

    public DBUser(Long id, String email, String username, String password, String role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}