package com.alloymobile.restapi.DTO;

import lombok.Data;

@Data
public class UsersDTO {

    private Long userId;
    private String username;
    private String password;

    public UsersDTO(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public UsersDTO(){

    }
}
