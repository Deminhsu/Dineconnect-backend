package com.ken.taroserver.mapper;

import com.ken.taropojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserProfileMapper {
    @Select("select * from user where id = #{id}")
    public User getByUserId(Long id);
}
