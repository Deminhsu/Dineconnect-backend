package com.ken.taroserver.mapper;

import com.ken.taropojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendMapper {

    @Select()
    public List<User> getFriendIdsByUserId(Long currentId);
}
