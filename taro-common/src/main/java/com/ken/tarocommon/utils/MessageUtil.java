package com.ken.tarocommon.utils;

import com.alibaba.fastjson.JSON;

class ResultMessageDTO {
    private boolean isSystem;
    private Long fromUserId;
    private Object message;
    // Getters and Setters
    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
    
}
public class MessageUtil {

    public static String getMessage(boolean isSystemMessage, Long userId, Object message) {
        ResultMessageDTO result = new ResultMessageDTO();
        result.setSystem(isSystemMessage);
        result.setMessage(message);
        if (userId != null) {
            result.setFromUserId(userId);
        }
        return JSON.toJSONString(result);
    }
}

