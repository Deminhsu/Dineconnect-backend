package com.ken.taroserver.mapper;

import com.ken.taropojo.dto.UserDTO;
import com.ken.taropojo.dto.UserFriendDTO;
import com.ken.taropojo.entity.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FriendMapper {

    // @Select("")
    // public List<User> getFriendIdsByUserId(Long currentId);

    @Insert("insert into friends (user_id, friend_id, status) VALUES(#{userId}, #{friendId}, #{status})")
    public void addByUserIds(UserFriendDTO userFriendDTO);

    @Insert("insert into friends (user_id, friend_id, status) VALUES(#{friendId}, #{userId}, #{status})")
    public void friendAddByUserIds(UserFriendDTO userFriendDTO);
    
    @Update("Update friends set status = 1 where user_id = #{userId} and friend_id = #{friendId} and status = 0")
    public void updateToFriendByUserIds(UserFriendDTO userFriendDTO);

    @Update("update friends set status = 1 where user_id = #{friendId} and friend_id = #{userId} and status = 0")
	public void updateFriendToUserByUserIds(UserFriendDTO userFriendDTO);
 
    // @Select("select friend_id from friends where user_id = #{userId} and status = 0")
    @Select("SELECT u.* FROM users u JOIN friends f ON u.user_id = f.friend_id WHERE f.user_id = #{userId} AND f.status = '0'")
	public List<UserDTO> getApplyFriendIdsByUserId(Long userId);

    // @Select("select friend_id from friends where user_id = #{userId} and status = 1")
    @Select("SELECT u.* FROM users u JOIN friends f ON u.user_id = f.friend_id WHERE f.user_id = #{userId} AND f.status = '1'")
    public List<UserDTO> getFriendIdsByUserId(Long userId);

    @Delete("delete from friends where user_id = #{userId} and friend_id = #{friendId} ")
    public void deleteFriendByUserId(Integer userId, Integer friendId);

}
