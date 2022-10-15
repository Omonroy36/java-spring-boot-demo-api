package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter @Setter
    @Column(name = "first_name", nullable = false)
    private String first_name;
    @Getter @Setter
    @Column(name = "last_name", nullable = false)
    private String last_name;
    @Getter @Setter
    @Column(name = "email", nullable = false)
    private String email;
    @Getter @Setter
    @Column(name = "password", nullable = false)
    private String password;

}
