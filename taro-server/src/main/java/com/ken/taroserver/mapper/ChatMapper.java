package com.ken.taroserver.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ken.taropojo.entity.ChatMessage;


@Mapper
public interface ChatMapper {

    @Select("select friend_id from friends where user_id = #{userId} and status = 1")
    public List<Long> getFriendIdsByUserId(Long userId);

    @Insert("INSERT INTO chat_message (sender_id, receiver_id, message, timestamp) VALUES (#{senderId}, #{receiverId}, #{message}, #{timeStamp})")
    public void insert(ChatMessage chatMessage);


    // 根据发送者ID查询聊天消息记录
    @Select("select * from chat_message where sender_id = #{senderId} and receiver_id = #{receiverId}")
    List<ChatMessage> selectChatMessagesBySenderId(Long senderId, Long receiverId);



}
