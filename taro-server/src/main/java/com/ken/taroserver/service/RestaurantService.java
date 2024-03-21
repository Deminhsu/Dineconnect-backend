package com.ken.taroserver.service;

import com.ken.taropojo.entity.Restaurant;

import jakarta.validation.OverridesAttribute.List;

public interface RestaurantService {
  Restaurant searchRestaurants(String name, String location);

}