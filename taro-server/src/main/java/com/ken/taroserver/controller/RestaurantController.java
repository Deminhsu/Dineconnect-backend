package com.ken.taroserver.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ken.tarocommon.result.Result;
import com.ken.taropojo.vo.RestaurantSearchVO;
import com.ken.taropojo.vo.RestaurantVO;
import com.ken.taroserver.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantService restaurantService;
    
    @PostMapping("/search")
    public Result<List<RestaurantSearchVO>> searchRestaurants(@RequestBody String name, String location) {
        List<RestaurantSearchVO> restaurantVOs = restaurantService.searchRestaurants(name, location);
        return Result.success(restaurantVOs);
    }

    @PostMapping("/search_who_want_to_eat")
    public Result<List<String>> searchUserIDWantToEat(@RequestBody String rest_id) {
        List<String> user_ids =  restaurantService.searchUserIDWantToEat(rest_id);
        return Result.success(user_ids);
    }



}
