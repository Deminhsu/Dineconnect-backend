package com.ken.taroserver.mapper;

import com.ken.taropojo.entity.Restaurant;
import com.ken.taropojo.entity.User;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RestaurantMapper {

  // @Insert("insert into restaurants (rest_name, rest_rating, image_url) VALUES (#{restName}, #{rating}, #{image_url})")
	// public void insert(Restaurant restaurantEntity);
  @Insert("INSERT INTO restaurants (rest_name, rest_rating, image_url) VALUES (#{restName}, #{rating}, #{url})")
  @Options(useGeneratedKeys = true, keyProperty = "restId")
  void insert(Restaurant restaurantEntity);

  @Select("select users.* from status_want_to_eats join users on users.user_id = status_want_to_eats.user_id where rest_id = #{rest_id}")
	public List<User> getUserByRestId(String rest_id);

    
}