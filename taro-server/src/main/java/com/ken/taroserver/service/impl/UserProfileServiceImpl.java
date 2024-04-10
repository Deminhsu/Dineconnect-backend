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
        userProfileDTO.setAvatar(user.getAvatar());
        return userProfileDTO;
    }




    @Override
    public void updateUserProfile(Long userId, MultipartFile image, UserDTO userProfileDTO) {
        UserDTO user = userProfileMapper.getByUserId(userId);

        // 確保上傳目錄存在
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        String filePath = null;
        if (image != null && !image.isEmpty()) {
            String originalFilename = image.getOriginalFilename();
            String extname = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + extname;
            filePath = uploadDir + newFileName; // 圖片的完整路徑
            log.info("新的文件名: {}", newFileName);

            try {
                image.transferTo(new File(filePath));
            } catch (IOException e) {
                log.error("Failed to save image", e);
               throw new RuntimeException("Failed to save image", e);
           }   
        }

        // 使用Lombok Builder模式来更新用户实体
        UserDTO updatedUser = user.builder()
                                .username(userProfileDTO.getUsername() == null ? user.getUsername() : userProfileDTO.getUsername())
                                .age(userProfileDTO.getAge() == 0 ? user.getAge() : userProfileDTO.getAge())
                                .sex(userProfileDTO.getSex() == null ? user.getSex() : userProfileDTO.getSex())
                                .avatar(filePath != null ? filePath : user.getAvatar()) // 使用新的头像路径，如果没有上传新图片则保持原有的路径
                                .build();
        
        // user.setUsername(userProfileDTO.getUsername());
        // user.setAge(userProfileDTO.getAge());
        // user.setSex(userProfileDTO.getSex());
        // user.setAvatar(userProfileDTO.getAvatar());

        userProfileMapper.update(user); // 更新用戶資料
    }



}
