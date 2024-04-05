package com.ken.taropojo.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ChatMessage {
    private Long senderId;
    private Long receiverId;
    private String message;
    private LocalDateTime timeStamp;
}