package com.ken.taroserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ken.taropojo.entity.User;
import com.ken.tarocommon.result.Result;
import com.ken.taropojo.vo.RestaurantSearchVO;
import com.ken.taroserver.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantService restaurantService;
    
    @GetMapping("/{name}/{location}")
    public Result<List<RestaurantSearchVO>> searchRestaurants(@PathVariable String name, @PathVariable String location) {
        List<RestaurantSearchVO> restaurantVOs = restaurantService.searchRestaurants(name, location);
        return Result.success(restaurantVOs);
    }

    @GetMapping("/{restId}")
    public Result<List<User>> searchUserIDWantToEat(@PathVariable String restId) {
        List<User> users =  restaurantService.searchUserIDWantToEat(restId);

        return Result.success(users);
    }

    // later send apply



}
