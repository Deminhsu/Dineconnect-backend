package com.ken.taroserver.service.impl;

import com.ken.taropojo.dto.UserProfileDTO;
import com.ken.taropojo.entity.User;
import com.ken.taroserver.mapper.UserProfileMapper;
import com.ken.taroserver.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserProfileMapper userProfileMapper;

    @Override
    public UserProfileDTO getUserProfile(Long id) {
        User user = userProfileMapper.getByUserId(id);
        return convertToDTO(user);
    }

    // 轉換 Entity 成 DTO 並回傳
    private UserProfileDTO convertToDTO(User user) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUsername(user.getUsername());
        userProfileDTO.setAge(user.getAge());
        userProfileDTO.setSex(user.getSex());
        userProfileDTO.setAvatar(user.getAvatar());

        return userProfileDTO;
    }


}
