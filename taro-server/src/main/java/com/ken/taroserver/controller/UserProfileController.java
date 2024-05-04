package com.ken.taroserver.controller;

import com.ken.tarocommon.context.BaseContext;
import com.ken.tarocommon.result.Result;
import com.ken.taropojo.dto.UserDTO;
import com.ken.taropojo.dto.UserProfileDTO;
import com.ken.taroserver.service.UserProfileService;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/user/profile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping()
    public Result<UserProfileDTO> getUserProfile() {
        Long userId = BaseContext.getCurrentId();
        UserProfileDTO userProfileDTO = userProfileService.getUserProfile(userId);
        return Result.success(userProfileDTO);
    }


   @PutMapping()
   public Result<String> updateUserProfile(@RequestBody UserDTO userProfileDTO) {
        Long userId = BaseContext.getCurrentId();
        userProfileDTO.setUserId(userId);
        userProfileService.updateUserProfile(userProfileDTO);
        return Result.success();
   }
   @PutMapping("/image")
   public Result<String> updateUserImage(@RequestParam("image") MultipartFile image) {
        Long userId = BaseContext.getCurrentId();
        userProfileService.updateUserImage(image, userId);
        return Result.success();
   }




}