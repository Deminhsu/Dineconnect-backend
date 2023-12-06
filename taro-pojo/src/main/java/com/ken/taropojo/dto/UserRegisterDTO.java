package com.ken.taropojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterDTO implements Serializable {
//    private Long id;
    private String username;
//    private String name;
    private String password;
    private String email;
//    private String phone; // null
//    private String sex; // null
}
