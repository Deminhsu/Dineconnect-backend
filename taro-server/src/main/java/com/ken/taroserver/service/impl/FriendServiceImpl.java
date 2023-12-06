package com.ken.taroserver.service.impl;

import com.ken.tarocommon.context.BaseContext;
import com.ken.taropojo.entity.User;
import com.ken.taroserver.mapper.FriendMapper;
import com.ken.taroserver.service.FriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public List<User> listFriends() {
        Long currentId = BaseContext.getCurrentId();
        return friendMapper.getFriendIdsByUserId(currentId);
    }
}
