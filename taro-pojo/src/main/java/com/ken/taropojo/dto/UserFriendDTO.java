package com.ken.taropojo.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "用戶使用好友功能傳遞的data model")
public class UserFriendDTO {
    private long friendId;
    private int status;
}
