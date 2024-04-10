package com.ken.taroserver.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ken.taropojo.entity.Restaurant;
import com.ken.taropojo.entity.User;
import com.ken.taropojo.vo.RestaurantSearchVO;
import com.ken.taropojo.vo.RestaurantVO;
import com.ken.taroserver.mapper.RestaurantMapper;
import com.ken.taroserver.service.RestaurantService;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantMapper restaurantMapper;

    private static final String API_KEY = "AIzaSyCMphdAs0HK11bkLCHVOsdTqTDzRk7Dy8U";
    private static final int DEFAULT_RADIUS = 10000;

    @Override
    public List<Restaurant> searchRestaurants(String name, String location) {
      GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();

      try {
          String query = name + " in " + location;
          PlacesSearchResponse response = PlacesApi.textSearchQuery(context, query).radius(DEFAULT_RADIUS).await();
          List<Restaurant> restaurants = new ArrayList<>();
          for (PlacesSearchResult result : response.results) {
              // 构建图片 URL
              String photoUrl = null;
              if (result.photos != null && result.photos.length > 0) {
                  String photoReference = result.photos[0].photoReference; // 获取图片引用
                  int maxWidth = 400; // 设置图片最大宽度，可根据实际需求调整
                  photoUrl = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=" + maxWidth +
                            "&photoreference=" + photoReference + "&key=" + API_KEY;
              }

              // 创建并添加 RestaurantSearchVO 实例
              Restaurant vo = Restaurant.builder()
                      .restName(result.name)
                      .rating(String.valueOf(result.rating)) // 假设rating是String
                      .address(result.formattedAddress)
                      .url(photoUrl) // 使用构建的图片 URL
                      .build();
                // 将VO转换为实体并保存到数据库，这里省略了转换的细节
              // Restaurant restaurantEntity = convertToEntity(vo);
              
              restaurantMapper.insert(vo);
                // 设置数据库自动生成的ID
              // vo.setId(restaurantEntity.getId());
              restaurants.add(vo);
          }

          // 随机挑选5间餐厅（如果少于5间则返回所有）
        //   Collections.shuffle(restaurants);
        //   return restaurants.subList(0, Math.min(restaurants.size(), 5));
          return restaurants;
      } catch (Exception e) {
          e.printStackTrace();
          return Collections.emptyList();
      }
    }
    // 假设有一个将VO转换为实体的方法
    private Restaurant convertToEntity(RestaurantSearchVO vo) {
        // 实现转换逻辑
        Restaurant restaurant = new Restaurant();
        BeanUtils.copyProperties(vo, restaurant);
        return restaurant;
    }

    @Override
    public List<User> searchUserIDWantToEat(String rest_id) {
        List<User> users = restaurantMapper.getUserByRestId(rest_id);
        return users;
    }
    

  
}