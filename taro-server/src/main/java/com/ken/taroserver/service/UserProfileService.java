package com.ken.taroserver.service;

import org.springframework.web.multipart.MultipartFile;

import com.ken.taropojo.dto.UserDTO;
import com.ken.taropojo.dto.UserProfileDTO;

public interface UserProfileService {
    UserProfileDTO getUserProfile(Long userId);
    void updateUserProfile(Long userId, MultipartFile image, UserDTO userProfileDTO);
}