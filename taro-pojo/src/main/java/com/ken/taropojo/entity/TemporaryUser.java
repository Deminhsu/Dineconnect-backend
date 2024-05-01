package com.ken.taropojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryUser implements Serializable {
    private Long userId;
    private String username;
    //    private String name;
    private String password;
    //    private String phone;
    private Integer age;
    private String sex; // 性別 0，1
    private String email;
    private String avatar; // 頭像
}
