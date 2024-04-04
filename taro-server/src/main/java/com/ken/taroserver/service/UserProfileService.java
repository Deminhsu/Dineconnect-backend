package com.ken.taroserver.service;

import com.ken.taropojo.dto.UserProfileDTO;

public interface UserProfileService {
    UserProfileDTO getUserProfile(Long userId);
//    void updateUserProfile(Long userId, UserProfileDTO userProfileDTO);
}