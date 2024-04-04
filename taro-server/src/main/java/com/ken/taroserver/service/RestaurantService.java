package com.ken.taroserver.service;
import com.ken.taropojo.vo.RestaurantSearchVO;
import java.util.List;
public interface RestaurantService {
  List<RestaurantSearchVO> searchRestaurants(String name, String location);

  List<String> searchUserIDWantToEat(String rest_id);

}