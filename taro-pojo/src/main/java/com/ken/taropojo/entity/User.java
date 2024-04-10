package com.ken.taropojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long userId;
    private String username;
//    private String name;
    private String password;
//    private String phone;
    private int age;
    private String sex; // 性別 0，1
    private String email;
    private String avatar; // 頭像
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
