package com.ken.taroserver.service.impl;

import com.ken.tarocommon.context.BaseContext;
import com.ken.taropojo.dto.UserFriendDTO;
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

    // @Override
    // public List<User> listFriends() {
        //     Long currentId = BaseContext.getCurrentId();
        //     return friendMapper.getFriendIdsByUserId(currentId);
        // }
        
    @Override
    public void sentApplyFriends(UserFriendDTO userFriendDTO) {
        userFriendDTO.setStatus(0);
        friendMapper.addByUserIds(userFriendDTO);
    }
        
    @Override
    public void addFriends(UserFriendDTO userFriendDTO) {
        userFriendDTO.setStatus(1);
        friendMapper.addByUserIds(userFriendDTO);
    }
    @Override
    public List<User> listApplyFriends(Long userId) {
        return friendMapper.getApplyFriendIdsByUserId(userId);
    }

    @Override
    public List<User> listFriends(Long userId) {
        return friendMapper.getFriendIdsByUserId(userId);
    }

    @Override
    public void deleteFriends(Integer userId, Integer friendId) {
        friendMapper.deleteFriendByUserId(userId, friendId);
    }


    
}
