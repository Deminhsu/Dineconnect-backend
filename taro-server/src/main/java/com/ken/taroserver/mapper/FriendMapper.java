package com.ken.taroserver.mapper;

import com.ken.taropojo.dto.UserFriendDTO;
import com.ken.taropojo.entity.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendMapper {

    // @Select("")
    // public List<User> getFriendIdsByUserId(Long currentId);

    @Insert("insert into friends (user_id, friend_id, status) VALUES(#{userId}, #{friendId}, #{status})")
    public void addByUserIds(UserFriendDTO userFriendDTO);

    @Select("select friend_id from friends where user_id = #{userId} and status = 0")
	public List<User> getApplyFriendIdsByUserId(Long userId);

    @Select("select friend_id from friends where user_id = #{userId} and status = 1")
    public List<User> getFriendIdsByUserId(Long userId);

    @Delete("delete from friends where user_id = #{userId} and friend_id = #{friendId} ")
    public void deleteFriendByUserId(Integer userId, Integer friendId);
}
