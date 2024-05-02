package com.ken.taroserver.controller;


import com.ken.tarocommon.constant.JwtClaimsConstant;
import com.ken.tarocommon.context.BaseContext;
import com.ken.tarocommon.properties.JwtProperties;
import com.ken.tarocommon.utils.JwtUtil;
import com.ken.taropojo.dto.UserLoginDTO;
import com.ken.taropojo.dto.UserRegisterDTO;
import com.ken.taropojo.dto.UserRestaurantDTO;
import com.ken.taropojo.entity.User;
import com.ken.taroserver.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ken.taropojo.vo.RestaurantVO;
import com.ken.taropojo.vo.UserLoginVO;
import com.ken.tarocommon.result.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.List;


@RestController
@RequestMapping("/user")
@Api(tags= "用戶相關接口")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/signup")
    @ApiOperation("註冊")
    public Result<String> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("用戶註冊");
        userService.signUp(userRegisterDTO);
        return Result.success();
    }

    @PostMapping("/signin")
    @ApiOperation("登入")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用戶登陸");
        User user = userService.oursLogin(userLoginDTO);


        // 登入成功，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        // { user_id : id }
        claims.put(JwtClaimsConstant.USER_ID, user.getUserId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getUserId())
                .userName(user.getUsername())
                .token(token)
                .build();
        return Result.success(userLoginVO);
    }
    @PostMapping("/signout")
    @ApiOperation("登出")
    public Result<String> signout() {
        log.info("用户登出");
        Long userId = BaseContext.getCurrentId(); 
        userService.signout(userId);
        return Result.success("登出成功");
    }
       
    @PostMapping("/restaurant")
    @ApiOperation("收藏餐廳")
    public Result<String> addFavoriteRestaurant(@RequestBody UserRestaurantDTO userRestaurantDTO) {
        Long userId = BaseContext.getCurrentId(); 
        userService.favoriteRestaurant(userRestaurantDTO, userId);
        return Result.success();

    }

    // todo
    // list like restaurant
    @GetMapping("/restaurant")
    public Result<List<RestaurantVO>> getFavoriteRestaurant() {
        Long userId = BaseContext.getCurrentId(); 
        List<RestaurantVO> restaurants = userService.getFavoriteRestaurant(userId);
        return Result.success(restaurants);
    }

    @DeleteMapping("/{restId}")
    public Result<String> deleteFavoriteRestaurant(@PathVariable Long restId) {
        Long userId = BaseContext.getCurrentId();
        userService.deleteFavoriteRestaurant(userId, restId);
        return Result.success();
    }
    
    
}
