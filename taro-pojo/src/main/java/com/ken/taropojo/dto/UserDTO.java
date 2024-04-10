package com.ken.taropojo.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private Long userId;
    private String username;
    private int age;
    private String sex;
    private String email;
    private String avatar;
    private Integer status;
    // 不包括password, createTime, updateTime
}
