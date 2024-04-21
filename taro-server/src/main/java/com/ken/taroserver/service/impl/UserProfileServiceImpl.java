package com.ken.taroserver.service.impl;

import com.ken.taropojo.dto.UserDTO;
import com.ken.taropojo.dto.UserProfileDTO;
import com.ken.taropojo.entity.User;
import com.ken.taroserver.mapper.UserProfileMapper;
import com.ken.taroserver.service.UserProfileService;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserProfileMapper userProfileMapper;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public UserProfileDTO getUserProfile(Long userId) {
        UserDTO user = userProfileMapper.getByUserId(userId);
        return convertToDTO(user);
    }

    // 轉換 Entity 成 DTO 並回傳
    private UserProfileDTO convertToDTO(UserDTO user) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUsername(user.getUsername());
        userProfileDTO.setAge(user.getAge());
        userProfileDTO.setSex(user.getSex());
        userProfileDTO.setImageUrl(user.getImageUrl());
        return userProfileDTO;
    }



    @Override
    public void updateUserProfile(UserDTO userProfileDTO) {
        // 如果上传了新的图片，则保存该图片并更新图片URL
        // 设置userId，这个值是操作的关键
        // userProfileDTO.setUserId(userId);

        // 更新用户资料，MyBatis将使用动态SQL进行更新
        log.info("使用者id: {}", userProfileDTO.getUserId());
        userProfileMapper.update(userProfileDTO);
    }
    @Override
    public void updateUserImage(MultipartFile image, Long userId) {
        UserDTO userProfileDTO = userProfileMapper.getByUserId(userId);
        // 如果上传了新的图片，则保存该图片并更新图片URL
        if (image != null && !image.isEmpty()) {
            String newAvatarPath = saveImage(image); // 保存图片并返回文件路径
            userProfileDTO.setImageUrl(newAvatarPath); // 更新DTO中的avatar字段
        }
        
        // 设置userId，这个值是操作的关键
        // userProfileDTO.setUserId(userId);

        // 更新用户资料，MyBatis将使用动态SQL进行更新
        log.info("使用者id: {}", userId);
        userProfileMapper.updateImage(userProfileDTO);
    }
    private String saveImage(MultipartFile image) {
        // 创建上传目录，如果它不存在的话
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists() && !uploadDirFile.mkdirs()) {
            log.error("Unable to create upload directory");
            throw new RuntimeException("Unable to create upload directory");
        }

        // 创建新的文件名
        String originalFilename = image.getOriginalFilename();
        String extname = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
        String newFileName = UUID.randomUUID() + extname;
        String filePath = uploadDir + newFileName; // 新图片的完整路径
        log.info("New file name: {}", newFileName);

        // 保存图片到指定的路径
        try {
            File newFile = new File(filePath);
            image.transferTo(newFile);
            return newFile.getAbsolutePath(); // 返回图片路径
        } catch (IOException e) {
            log.error("Failed to save image: {}", newFileName, e);
            throw new RuntimeException("Failed to save image", e);
        }
    }


}
