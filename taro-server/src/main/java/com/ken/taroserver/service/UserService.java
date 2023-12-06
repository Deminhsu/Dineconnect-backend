package com.ken.taroserver.service;

import com.ken.taropojo.dto.UserLoginDTO;
import com.ken.taropojo.dto.UserRegisterDTO;
import com.ken.taropojo.entity.User;

public interface UserService {
    User oursLogin(UserLoginDTO userLoginDTO);
    void signUp(UserRegisterDTO userRegisterDTO);
}
