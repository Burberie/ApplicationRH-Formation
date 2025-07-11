package com.mgas.formation.entity;

import com.mgas.formation.enumeration.ContractEnum;
import com.mgas.formation.enumeration.RoleEnum;
import com.mgas.formation.enumeration.ServiceEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "register_num")
    private String register_num;
    @Setter(AccessLevel.NONE)
    @Column(name = "register_date")
    private Date register_date = new Date();
    @Column(name = "seniority_date")
    private Date seniority_date;
    @Column(name = "end_date")
    private Date end_date;
    @ElementCollection(targetClass = RoleEnum.class)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private Set<RoleEnum> role;
    @Enumerated(EnumType.STRING)
    @Column(name = "service")
    private ServiceEnum service;
    @Enumerated(EnumType.STRING)
    @Column(name = "contract")
    private ContractEnum contract;
    @Column(name = "isActive")
    private boolean isActive = true;

    public User() {}

    public User(Long id, String email, String username, String password, String lastname, String firstname, String register_num, Date seniority_date, Date end_date, Set<RoleEnum> role, ServiceEnum service, ContractEnum contract) {
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