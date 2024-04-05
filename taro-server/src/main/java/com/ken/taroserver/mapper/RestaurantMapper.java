package com.ken.taroserver.mapper;

import com.ken.taropojo.entity.Restaurant;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RestaurantMapper {

  @Insert("insert into restaurants (rest_name, rest_rating, image_url) VALUES (#{rest_name}, #{rest_rating}, #{image_url})")
	public void insert(Restaurant restaurantEntity);

  @Select("select user_id from status_want_to_eats where rest_id = #{rest_id}")
	public List<String> getByRestId(String rest_id);

    
}