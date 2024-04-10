package com.ken.taroserver.controller;

import com.ken.tarocommon.result.Result;
import com.ken.taropojo.dto.UserDTO;
import com.ken.taropojo.dto.UserProfileDTO;
import com.ken.taroserver.service.UserProfileService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user/profile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/{userId}")
    public Result<UserProfileDTO> getUserProfile(@PathVariable Long userId) {
        UserProfileDTO userProfileDTO = userProfileService.getUserProfile(userId);
        return Result.success(userProfileDTO);
    }


   @PutMapping()
   public Result<String> updateUserProfile(@RequestBody UserDTO userProfileDTO) {
       userProfileService.updateUserProfile(userProfileDTO);
       return Result.success();
   }
   @PutMapping("/image/{userId}")
   public Result<String> updateUserImage(@PathVariable Long userId, @RequestParam(value = "image") MultipartFile image) {
       userProfileService.updateUserImage(image, userId);
       return Result.success();
   }
}