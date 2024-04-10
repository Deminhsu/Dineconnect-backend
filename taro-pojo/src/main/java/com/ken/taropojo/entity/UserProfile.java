package com.ken.taropojo.entity;

import lombok.Data;

@Data
public class UserProfile {
  
    private Long userId;
    private String username;
    private int age;
    private String sex; // 性別 0，1
    private String avatar; // 頭像
    private Integer status;
}
