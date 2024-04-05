package com.ken.taroserver.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ken.tarocommon.result.Result;
import com.ken.taropojo.entity.ChatMessage;
import com.ken.taroserver.mapper.ChatMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class ChatController {

    @Autowired
    private ChatMapper chatMapper; // 假设这个Mapper有方法来获取聊天记录

    // 获取两个用户之间的聊天记录
    @GetMapping("/chat/history/{userId}/{friendId}")
    public Result<List<ChatMessage>> getChatHistory(@PathVariable Long userId, @PathVariable Long friendId) {
        // 获取由用户发送给朋友的消息
        List<ChatMessage> userToFriend = chatMapper.selectChatMessagesBySenderId(userId, friendId);
        
        // 获取由朋友发送给用户的消息
        List<ChatMessage> friendToUser = chatMapper.selectChatMessagesBySenderId(friendId, userId);
        
        // 合并这两个列表
        List<ChatMessage> combinedMessages = Stream.concat(userToFriend.stream(), friendToUser.stream())
                                                    .sorted((m1, m2) -> m1.getTimeStamp().compareTo(m2.getTimeStamp()))
                                                    .collect(Collectors.toList());
        // 返回排序后的消息列表
        return Result.success(combinedMessages);
    }
}