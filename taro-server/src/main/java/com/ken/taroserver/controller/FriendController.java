package com.ken.taroserver.controller;

import com.ken.tarocommon.result.Result;
import com.ken.taropojo.dto.UserFriendDTO;
import com.ken.taropojo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/friends")
@Slf4j
public class FriendController {



    //好友列表
    @GetMapping
    public Result<List<User>> listFriends() {

    }

    // 添加好友
    @PostMapping
    public Result<String> addFriends(@RequestBody UserFriendDTO userFriendDTO) {

    }

    // 刪除好友
    @DeleteMapping("{userId}/{friendID}")
    public Result<String> deleteFriends(@PathVariable Integer userId, @PathVariable Integer friendID) {

    }
}
