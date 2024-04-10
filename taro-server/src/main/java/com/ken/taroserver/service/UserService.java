package com.ken.taroserver.service;

import java.util.List;

import com.ken.taropojo.dto.UserLoginDTO;
import com.ken.taropojo.dto.UserRegisterDTO;
import com.ken.taropojo.dto.UserRestaurantDTO;
import com.ken.taropojo.entity.User;
import com.ken.taropojo.vo.RestaurantVO;

public interface UserService {
    User oursLogin(UserLoginDTO userLoginDTO);
    void signUp(UserRegisterDTO userRegisterDTO);
    void favoriteRestaurant(UserRestaurantDTO userRestaurantDTO);
    void quickAssign(Integer userIDSelf, Integer userIDOthers);
    List<RestaurantVO> getFavoriteRestaurant(long userId);
    void signout(Long userId);
}
