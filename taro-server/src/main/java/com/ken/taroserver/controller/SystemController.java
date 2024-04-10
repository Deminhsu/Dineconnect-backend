package com.ken.taroserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ken.tarocommon.result.Result;
import com.ken.taropojo.dto.UserDTO;
import com.ken.taropojo.entity.User;
import com.ken.taroserver.service.SystemService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/System")
@Slf4j
public class SystemController {

    @Autowired
    private SystemService systemService;

    @GetMapping("/{userIDSelf}")
    public Result<List<UserDTO>> quickAssign(@PathVariable Integer userIDSelf) {
        List<UserDTO> users = systemService.quickAssign(userIDSelf);
        return Result.success(users);
    }


}
