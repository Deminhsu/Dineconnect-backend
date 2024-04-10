package com.ken.taroserver.service;

import java.util.List;

import com.ken.taropojo.dto.UserDTO;
import com.ken.taropojo.entity.User;

public interface SystemService {

	List<UserDTO> quickAssign(Integer userIDSelf);    

}
