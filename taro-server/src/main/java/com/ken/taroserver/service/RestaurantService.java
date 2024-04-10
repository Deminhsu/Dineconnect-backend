package com.ken.taroserver.service;
import com.ken.taropojo.entity.Restaurant;
import com.ken.taropojo.entity.User;
import com.ken.taropojo.vo.RestaurantSearchVO;
import java.util.List;
public interface RestaurantService {
  List<Restaurant> searchRestaurants(String name, String location);

  List<User> searchUserIDWantToEat(String rest_id);

}