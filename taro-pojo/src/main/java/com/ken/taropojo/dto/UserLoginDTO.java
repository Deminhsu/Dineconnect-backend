package com.ken.taropojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "用戶登陸時傳遞的數據模型")
public class UserLoginDTO implements Serializable {
//    private String code;
    @ApiModelProperty("用戶名")
    private String username;

    @ApiModelProperty("密碼")
    private String password;
}

