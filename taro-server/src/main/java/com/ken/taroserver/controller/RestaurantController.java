package com.ken.taroserver.controller;

import com.ken.tarocommon.context.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result<List<RestaurantSearchVO>> getRestaurants(@PathVariable String name, @PathVariable String location) {
        List<RestaurantSearchVO> restaurants = restaurantService.searchRestaurants(name, location);
        return Result.success(restaurants);
    }

    @GetMapping("/{restId}")
    public Result<List<User>> getUsersWantToEat(@PathVariable String restId) {
        List<User> users =  restaurantService.searchUserIDWantToEat(restId);

        return Result.success(users);
    }

    @PostMapping("/{restId}")
    public Result<String> addUsersWantToEat(@PathVariable Long restId) {
        Long userId = BaseContext.getCurrentId();
        restaurantService.addUsersWantToEat(userId, restId);
        return Result.success();
    }

    // later send apply



}
