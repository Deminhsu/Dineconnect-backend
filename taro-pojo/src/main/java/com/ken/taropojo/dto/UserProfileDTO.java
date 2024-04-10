package com.ken.taropojo.dto;

import lombok.Data;

@Data
public class UserProfileDTO {
    private String username;
    private Integer age;
    private String sex;
    private String avatar; // 頭像url
}
