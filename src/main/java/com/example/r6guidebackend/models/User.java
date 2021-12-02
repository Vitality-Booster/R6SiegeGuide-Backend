package com.example.r6guidebackend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String fullName;

    @Column(unique=true)
    private String username;

    @Column(unique=true)
    private String email;

    private boolean admin = false;
    private String password;

}
