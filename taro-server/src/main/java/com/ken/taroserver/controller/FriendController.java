package com.ken.taroserver.controller;

import com.ken.tarocommon.result.Result;
import com.ken.taropojo.dto.UserFriendDTO;
import com.ken.taropojo.entity.User;
import com.ken.taroserver.service.FriendService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/friends")
@Slf4j
public class FriendController {

    @Autowired
    private FriendService friendService;

    // 申請中的好友列表
    @GetMapping("/apply/{userId}")
    public Result<List<User>> listApplyFriends(@PathVariable Long userId) {
        List<User> applyUsers = friendService.listApplyFriends(userId);
        return Result.success(applyUsers);
    }
    // 好友列表
    @GetMapping("/list/{userId}")
    public Result<List<User>> listFriends(@PathVariable Long userId) {
        List<User> friends =friendService.listFriends(userId);
        return Result.success(friends);
    }

    
    // 申請好友 
    @PostMapping("/sentApply")
    public Result<String> sentApplyFriends(@RequestBody UserFriendDTO userFriendDTO) {
        friendService.sentApplyFriends(userFriendDTO);
        return Result.success();
    }
    

    // 確認添加好友, checking
    @PostMapping()
    public Result<String> addFriends(@RequestBody UserFriendDTO userFriendDTO) {
        friendService.addFriends(userFriendDTO);
        return Result.success();
    }

    
    // 刪除好友、拒絕好友
    @DeleteMapping("{userID}/{friendID}")
    public Result<String> deleteFriends(@PathVariable Integer userId, @PathVariable Integer friendId) {
        friendService.deleteFriends(userId, friendId);
        return Result.success();
    }
}
