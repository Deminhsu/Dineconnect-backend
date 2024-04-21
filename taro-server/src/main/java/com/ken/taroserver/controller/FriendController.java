package com.ken.taroserver.controller;

import com.ken.tarocommon.context.BaseContext;
import com.ken.tarocommon.result.Result;
import com.ken.taropojo.dto.UserDTO;
import com.ken.taropojo.dto.UserFriendDTO;
import com.ken.taropojo.entity.User;
import com.ken.taroserver.service.FriendService;


import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
@Slf4j
public class FriendController {

    @Autowired
    private FriendService friendService;

    // 申請中的好友列表
    @GetMapping("/apply")
    public Result<List<UserDTO>> listApplyFriends() {
        Long userId = BaseContext.getCurrentId();
        List<UserDTO> applyUsers = friendService.listApplyFriends(userId);
        return Result.success(applyUsers);
    }
    // 好友列表
    @GetMapping()
    public Result<List<UserDTO>> listFriends() {
        Long userId = BaseContext.getCurrentId();
        List<UserDTO> friends = friendService.listFriends(userId);
        return Result.success(friends);
    }

    
    // 申請好友 
    @PostMapping("/apply")
    public Result<String> sentApplyFriends(@RequestBody UserFriendDTO userFriendDTO) {
        Long userId = BaseContext.getCurrentId();
        friendService.sentApplyFriends(userFriendDTO, userId);
        return Result.success();
    }
    

    // 確認添加好友, checking
    @PostMapping()
    public Result<String> addFriends(@RequestBody UserFriendDTO userFriendDTO) {
        Long userId = BaseContext.getCurrentId();
        friendService.addFriends(userFriendDTO, userId);
        return Result.success();
    }

    
    // 刪除好友、拒絕好友
    @DeleteMapping("/{friendId}")
    public Result<String> deleteFriends(@PathVariable Long friendId) {
        Long userId = BaseContext.getCurrentId();
        friendService.deleteFriends(userId, friendId);
        return Result.success();
    }
}
