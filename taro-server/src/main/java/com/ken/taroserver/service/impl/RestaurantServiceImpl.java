package com.ken.taroserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ken.taropojo.entity.Restaurant;
import com.ken.taroserver.mapper.RestaurantMapper;
import com.ken.taroserver.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {
  
  
  @Autowired
  private RestaurantMapper restaurantMapper;
  
  public Restaurant searchRestaurants(String name, String location) {
    return null;
  }
}