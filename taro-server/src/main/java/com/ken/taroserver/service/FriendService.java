package com.ken.taroserver.service;

import com.ken.taropojo.dto.UserDTO;
import com.ken.taropojo.dto.UserFriendDTO;
import com.ken.taropojo.entity.User;

import java.util.List;

public interface FriendService {

	void sentApplyFriends(UserFriendDTO userFriendDTO);
    void addFriends(UserFriendDTO userFriendDTO);

    List<UserDTO> listApplyFriends(Long userId);

    List<UserDTO> listFriends(Long userId);

	void deleteFriends(Integer userId, Integer friendId);

    

}
