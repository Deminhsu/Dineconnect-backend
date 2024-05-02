package com.ken.taroserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ken.taropojo.entity.Restaurant;
import com.ken.taropojo.entity.User;
import com.ken.tarocommon.result.Result;
import com.ken.taropojo.vo.RestaurantSearchVO;
import com.ken.taroserver.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    
    @GetMapping("/{name}/{location}")
    public Result<List<Restaurant>> getRestaurants(@PathVariable String name, @PathVariable String location) {
        List<Restaurant> restaurants = restaurantService.searchRestaurants(name, location);
        return Result.success(restaurants);
    }

    @GetMapping("/{restId}")
    public Result<List<User>> getUsersWantToEat(@PathVariable String restId) {
        List<User> users =  restaurantService.searchUserIDWantToEat(restId);

        return Result.success(users);
    }

    // later send apply



}
