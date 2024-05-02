package com.ken.taroserver.mapper;

import com.ken.taropojo.entity.Restaurant;
import com.ken.taropojo.entity.User;

import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface RestaurantMapper {

   @Insert("insert into restaurants (rest_name, rest_rating, image_url, address) VALUES (#{restName}, #{rating}, #{url}, #{address})")
   void insertRes(Restaurant restaurantEntity);
//   @Select("select * from restaurants where rest_name = #{name}")
//   Restaurant getFullRestaurant(String name);
//  @Insert("INSERT INTO restaurants (rest_name, rest_rating, image_url) VALUES (#{restName}, #{rating}, #{url})")
//  @Options(useGeneratedKeys = true, keyProperty = "restId")
//  void insert(Restaurant restaurantEntity);

  @Select("select users.* from status_want_to_eats join users on users.user_id = status_want_to_eats.user_id where rest_id = #{rest_id}")
	public List<User> getUserByRestId(String rest_id);
  @Results(value={
          @Result(property="restId", column="rest_id", javaType=Long.class),
          @Result(property="restName", column="rest_name"),
          @Result(property="rating", column="rest_rating"),
          @Result(property="url", column="image_url"),
          @Result(property="address", column="address")
  })
  @Select("select * from restaurants where rest_name = #{name}")
  Restaurant findByRestName(String name);

    
}