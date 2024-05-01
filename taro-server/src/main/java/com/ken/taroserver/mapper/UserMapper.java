package com.ken.taroserver.mapper;

import com.ken.taropojo.dto.UserRestaurantDTO;
import com.ken.taropojo.entity.User;
import com.ken.taropojo.vo.RestaurantVO;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

//    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("insert into users (username, password, email, create_time, update_time) " +
            "VALUES (#{username}, #{password}, #{email}, #{createTime}, #{updateTime})")
    public void signUp(User user);

    @Select("select * from users where email = #{email}")
    public User getByEmail(String email);

    // @Insert("insert into favorite_restaurants (user_id, rest_id) VALUES (#{userId}, #{restaurantId})")
    // public void insertRestaurant(UserRestaurantDTO userRestaurantDTO, Long userId);
    @Insert("insert into favorite_restaurants (user_id, rest_id) VALUES (#{userId}, #{userRestaurantDTO.restaurantId})")
    public void insertRestaurant(@Param("userRestaurantDTO") UserRestaurantDTO userRestaurantDTO, @Param("userId") Long userId);

    @Select("SELECT " +
        "restaurants.rest_id AS id, " +
        "restaurants.rest_name AS name, " +
        "restaurants.rest_rating AS rating, " +
        "restaurants.image_url AS url, " +
        "restaurants.address " +
        "FROM favorite_restaurants " +
        "JOIN restaurants ON restaurants.rest_id = favorite_restaurants.rest_id " +
        "WHERE favorite_restaurants.user_id = #{userId}")
    public List<RestaurantVO> listFavoriteRestaurant(long userId);



    @Update("Update users set status = 1 where email = #{email}")
    public void changeStateByEmail(String email);

    @Update("UPDATE users SET state = 0 WHERE id = #{userId}")
    void updateUserState(Long userId);

}
