package com.ken.taroserver.controller;


import com.ken.tarocommon.constant.JwtClaimsConstant;
import com.ken.tarocommon.properties.JwtProperties;
import com.ken.tarocommon.utils.JwtUtil;
import com.ken.taropojo.dto.UserLoginDTO;
import com.ken.taropojo.dto.UserRegisterDTO;
import com.ken.taropojo.entity.User;
import com.ken.taroserver.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ken.taropojo.vo.UserLoginVO;
import com.ken.tarocommon.result.Result;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags= "用戶相關接口")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/register")
    @ApiOperation("註冊")
    public Result<String> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("用戶註冊");
        userService.signUp(userRegisterDTO);
        return Result.success();
    }

    @PostMapping("/login")
    @ApiOperation("登入")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用戶登陸");
        User user = userService.oursLogin(userLoginDTO);

        // 登入成功，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        // { user_id : id }
        claims.put(JwtClaimsConstant.USER_ID,user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .token(token)
                .build();
        return Result.success(userLoginVO);
    }

    @PostMapping("/favorite_restaurant")
    @ApiOperation("收藏餐廳")
    public Result<String> favoriteRestaurant(@RequestBody Integer restaurantId, Integer userId) {
        userService.favoriteRestaurant(restaurantId, userId);
        return Result.success();

    }

}
