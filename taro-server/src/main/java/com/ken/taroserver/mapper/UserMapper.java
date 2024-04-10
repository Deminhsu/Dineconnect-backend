package com.ken.taroserver.mapper;

import com.ken.taropojo.dto.UserRestaurantDTO;
import com.ken.taropojo.entity.User;
import com.ken.taropojo.vo.RestaurantVO;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

//    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("insert into users (username, password, email, create_time, update_time) " +
            "VALUES (#{username}, #{password}, #{email}, #{createTime}, #{updateTime})")
    public void signUp(User user);

    @Select("select * from users where username = #{username}")
    public User getByUsername(String username);

    @Insert("insert into favorite_restaurants (user_id, rest_id) VALUES (#{userId}, #{restaurantId})")
    public void insertRestaurant(UserRestaurantDTO userRestaurantDTO);

    @Select("select Restaurants.* from favorite_restaurants join restaurants on Restaurants.rest_id = favorite_restaurants.rest_id where favorite_restaurants.userId = #{userId}")
    public List<RestaurantVO> listFavoriteRestaurant(long userId);

    @Update("Update users set status = 1 where username = #{username}")
    public void changeStateByUserName(String username);

    @Update("UPDATE users SET state = 0 WHERE id = #{userId}")
    void updateUserState(Long userId);

}
