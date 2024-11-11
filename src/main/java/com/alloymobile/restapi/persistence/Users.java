package com.alloymobile.restapi.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    public Users(){

    }

    public Users(Long userId, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
