package com.ken.taroserver.ws;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ken.tarocommon.utils.MessageUtil;
import com.ken.taropojo.entity.ChatMessage;
import com.ken.taropojo.entity.Message;
import com.ken.taroserver.mapper.ChatMapper;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/{userId}")
@Component
public class ChatEndpoint {
    private static final Map<Long, Session> onlineUsers = new ConcurrentHashMap<>();

    @Autowired
    ChatMapper chatMapper;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Long userId) {
        onlineUsers.put(userId, session);
        String message = MessageUtil.getMessage(true, null, listFriends(userId));
        broadcast(message);

        
    }

    private Object listFriends(Long userId) {
      List<Long> friendIds = chatMapper.getFriendIdsByUserId(userId);
      Set<Long> onlineUserIds = onlineUsers.keySet();
      friendIds.retainAll(onlineUserIds);
      return friendIds;
    }
    

    @OnMessage
    public void onMessage(String message, @PathParam("userId") Long userId) {
        Message msg = JSON.parseObject(message, Message.class);
        Long toUserId = msg.getToUserId();
        String msgs = msg.getMessage();
        Session session = onlineUsers.get(toUserId); // toUserId session
        // 创建消息对象
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSenderId(userId);
        chatMessage.setReceiverId(toUserId);
        chatMessage.setMessage(msgs);
        chatMessage.setTimeStamp(LocalDateTime.now());
        chatMapper.insert(chatMessage);

        String message1 = MessageUtil.getMessage(false, userId, msgs);
        try {
          session.getBasicRemote().sendText(message1);
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("userId") Long userId) {
        onlineUsers.remove(userId);
        String message = MessageUtil.getMessage(true, null, listFriends(userId));
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Handle error
    }

    private void broadcast(String message) {
        // Set<Map.Entry<String, Session>> entries = onlineUsers.entrySet();
        onlineUsers.values().forEach(session -> {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
