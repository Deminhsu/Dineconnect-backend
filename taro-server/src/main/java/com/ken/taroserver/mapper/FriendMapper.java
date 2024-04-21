package com.ken.taroserver.mapper;

import com.ken.taropojo.dto.UserDTO;
import com.ken.taropojo.dto.UserFriendDTO;
import com.ken.taropojo.entity.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FriendMapper {

    // @Select("")
    // public List<User> getFriendIdsByUserId(Long currentId);

    @Insert("insert into friends (user_id, friend_id, status) VALUES(#{userId}, #{userFriendDTO.friendId}, #{userFriendDTO.status})")
    public void addByUserIds(@Param("userFriendDTO") UserFriendDTO userFriendDTO, @Param("userId") Long userId);

    @Insert("insert into friends (user_id, friend_id, status) VALUES(#{userFriendDTO.friendId}, #{userId}, #{userFriendDTO.status})")
    public void friendAddByUserIds(@Param("userFriendDTO") UserFriendDTO userFriendDTO, @Param("userId") Long userId);
    
    @Update("Update friends set status = 1 where user_id = #{userId} and friend_id = #{userFriendDTO.friendId} and status = 0")
    public void updateToFriendByUserIds(@Param("userFriendDTO") UserFriendDTO userFriendDTO, @Param("userId") Long userId);

    @Update("update friends set status = 1 where user_id = #{userFriendDTO.friendId} and friend_id = #{userId} and status = 0")
	public void updateFriendToUserByUserIds(@Param("userFriendDTO") UserFriendDTO userFriendDTO, @Param("userId") Long userId);
 
    // @Select("select friend_id from friends where user_id = #{userId} and status = 0")
    @Select("SELECT u.* FROM users u JOIN friends f ON u.user_id = f.friend_id WHERE f.user_id = #{userId} AND f.status = '0'")
	public List<UserDTO> getApplyFriendIdsByUserId(Long userId);

    // @Select("select friend_id from friends where user_id = #{userId} and status = 1")
    @Select("SELECT u.* FROM users u JOIN friends f ON u.user_id = f.friend_id WHERE f.user_id = #{userId} AND f.status = '1'")
    public List<UserDTO> getFriendIdsByUserId(Long userId);

    @Delete("delete from friends where user_id = #{userId} and friend_id = #{friendId} ")
    public void deleteFriendByUserId(Long userId, Long friendId);

}
