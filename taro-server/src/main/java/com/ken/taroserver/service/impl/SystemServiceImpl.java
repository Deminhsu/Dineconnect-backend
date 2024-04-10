package com.ken.taroserver.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ken.taropojo.dto.UserDTO;
import com.ken.taropojo.entity.User;
import com.ken.taroserver.mapper.SystemMapper;
import com.ken.taroserver.service.SystemService;
import com.ken.taroserver.ws.ChatEndpoint;

@Service
public class SystemServiceImpl implements SystemService  {
  @Autowired
  private SystemMapper systemMapper;

  @Override
  public List<UserDTO> quickAssign(Integer userIDSelf) {
      // Set<Long> onlineUserIds = ChatEndpoint.getOnlineUsers();

      // // 将 userIDSelf 从 Integer 转换为 Long，并从集合中移除
      // onlineUserIds.remove(userIDSelf.longValue());
      // List<User> users = systemMapper.getUsersById(onlineUserIds);
      // return users;
      List<UserDTO> users = systemMapper.getUsersByStateExcludingUser(userIDSelf);
      return users;
  }

}
