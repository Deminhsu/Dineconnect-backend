package com.ken.taroserver.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ken.taropojo.dto.UserDTO;
import com.ken.taropojo.entity.User;

@Mapper
public interface SystemMapper {
  // @Select("<script>" +
  //           "SELECT * FROM users " +
  //           "WHERE id IN " +
  //           "<foreach item='userId' collection='onlineUserIds' open='(' separator=',' close=')'>" +
  //           "#{userId}" +
  //           "</foreach>" +
  //           "</script>")
  // List<User> getUsersById(@Param("onlineUserIds") Set<Long> onlineUserIds);
	// List<User> getUsersById(Set<Long> onLineUserIds);
  
  // @Select("selsect * from users where state = 1")
  // List<User> getUsersByState();
  @Select("SELECT * FROM users WHERE status = 1 AND id <> #{userId}")
  List<UserDTO> getUsersByStateExcludingUser(@Param("userId") Integer userIDSelf);
}
