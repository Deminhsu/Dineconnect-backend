package com.ken.taropojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterPart2DTO implements Serializable {
    private String username;
    private String sex;
    private Integer age;
    private String avatar; // 頭像
}
