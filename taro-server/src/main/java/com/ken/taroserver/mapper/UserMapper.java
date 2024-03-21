package com.ken.taroserver.mapper;

import com.ken.taropojo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

//    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("insert into user (username, password, email, create_time, update_time) " +
            "VALUES (#{username}, #{password}, #{email}, #{createTime}, #{updateTime})")
    public void signUp(User user);

    @Select("select * from user where username = #{username}")
    public User getByUsername(String username);

    @Insert("insert into user_favorite (user_id, restaurant_id) VALUES (#{userId}, #{restaurantId})")
    public void insertRestaurant(Integer restaurantId, Integer userId);

}
