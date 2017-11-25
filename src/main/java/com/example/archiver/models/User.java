package com.example.archiver.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    public User() {}

    public User(long id) {
        this.id = id;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public long getId(){
        return this.id;
    }
}
