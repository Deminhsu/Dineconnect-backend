package com.ken.taropojo.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Builder(toBuilder = true)
// @NoArgsConstructor
// @AllArgsConstructor
@Data
public class UserDTO {
    private Long userId;
    private String username;
    private int age;
    private String sex;
    private String email;
    private String avatar;
    // 不包括password, createTime, updateTime
}
