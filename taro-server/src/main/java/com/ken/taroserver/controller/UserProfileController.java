package com.ken.taroserver.controller;

import com.ken.tarocommon.result.Result;
import com.ken.taropojo.dto.UserProfileDTO;
import com.ken.taroserver.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private UserProfileService userprofileService;

    @GetMapping("/{userId}")
    public Result<UserProfileDTO> getUserProfile(@PathVariable Long userId) {
        UserProfileDTO userProfileDTO = userprofileService.getUserProfile(userId);
        return Result.success(userProfileDTO);
    }


//    @PutMapping("/{userId}")
//    public ResponseEntity<String> updateUserProfile(@PathVariable Long userId, @RequestBody UserProfileDTO userProfileDTO) {
//        profileService.updateUserProfile(userId, userProfileDTO);
//        return ResponseEntity.ok("Profile updated successfully");
//    }

}