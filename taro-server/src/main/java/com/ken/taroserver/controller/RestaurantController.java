package com.ken.taroserver.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ken.tarocommon.result.Result;
import com.ken.taropojo.vo.RestaurantVO;

import jakarta.validation.OverridesAttribute.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    // TODO
    public Result<RestaurantVO> searchRestaurants(@RequestBody String name, String location) {
        return null;
    }

}
