package com.ken.taroserver.service.impl;

import com.ken.tarocommon.constant.MessageConstant;
import com.ken.tarocommon.exception.AccountNotFoundException;
import com.ken.tarocommon.exception.PasswordErrorException;
import com.ken.taropojo.dto.UserLoginDTO;
import com.ken.taropojo.dto.UserRegisterDTO;
import com.ken.taropojo.entity.User;
import com.ken.taroserver.mapper.UserMapper;
import com.ken.taroserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void signUp(UserRegisterDTO userRegisterDTO) {
        User user = User.builder()
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();
        BeanUtils.copyProperties(userRegisterDTO, user);

        // encode password
        // 使用BCrypt加密密码
        String encodedPassword = BCrypt.hashpw(userRegisterDTO.getPassword(), BCrypt.gensalt());
        user.setPassword(encodedPassword);

        userMapper.signUp(user);
    }

    public User oursLogin(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        User user = userMapper.getByUsername(username);

        if (user == null) {
            // 用户名不存在
            // 可以抛出自定义的异常或返回适当的响应
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        // 2、校验用户输入的密码与数据库中的密码是否匹配
        // 使用者輸入 vs 資料庫存的password
        if(!BCrypt.checkpw(password, user.getPassword())) {
            // 密码不正确
            // 可以抛出自定义的异常或返回适当的响应
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        // 3、处理其他异常情况（账号被锁定等）
        // Todo

        // 如果一切正常，返回用户信息
        return user;

    }

    public void favoriteRestaurant(Integer restaurantId, Integer userId) {
        userMapper.insertRestaurant(restaurantId, userId);
    }

    public void quickAssign(Integer userIDSelf, Integer userIDOthers) {
        userMapper.
    }
}
