package com.ken.taroserver.service;

import com.ken.taropojo.dto.UserDTO;
import com.ken.taropojo.dto.UserFriendDTO;
import com.ken.taropojo.entity.User;

import java.util.List;

public interface FriendService {

	void sentApplyFriends(UserFriendDTO userFriendDTO, Long userId);
    void addFriends(UserFriendDTO userFriendDTO, Long userId);

    List<UserDTO> listApplyFriends(Long userId);

    List<UserDTO> listFriends(Long userId);

	void deleteFriends(Long userId, Long friendId);

    

}
