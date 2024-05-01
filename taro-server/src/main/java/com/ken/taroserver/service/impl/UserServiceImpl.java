package com.ken.taroserver.service.impl;

import com.ken.tarocommon.constant.MessageConstant;
import com.ken.tarocommon.exception.AccountNotFoundException;
import com.ken.tarocommon.exception.PasswordErrorException;
import com.ken.taropojo.dto.UserLoginDTO;
import com.ken.taropojo.dto.UserRegisterDTO;
import com.ken.taropojo.dto.UserRestaurantDTO;
import com.ken.taropojo.entity.User;
import com.ken.taropojo.vo.RestaurantVO;
import com.ken.taroserver.mapper.UserMapper;
import com.ken.taroserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

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
        String email = userLoginDTO.getEmail();
        String password = userLoginDTO.getPassword();

        userMapper.changeStateByEmail(email);
        //1、根据用户名查询数据库中的数据
        User user = userMapper.getByEmail(email);

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

    public void favoriteRestaurant(UserRestaurantDTO userRestaurantDTO, Long userId) {
        userMapper.insertRestaurant(userRestaurantDTO, userId);
    }

    public void quickAssign(Integer userIDSelf, Integer userIDOthers) {
        // userMapper.
    }

    @Override
    public List<RestaurantVO> getFavoriteRestaurant(long userId) {
        // TODO Auto-generated method stub
        return userMapper.listFavoriteRestaurant(userId);
        
    }

    @Override
    public void signout(Long userId) {
        // 假设这是登出逻辑的一部分
        userMapper.updateUserState(userId);
        // 可能还有其他登出处理逻辑，比如使 JWT 无效等
    }
}
