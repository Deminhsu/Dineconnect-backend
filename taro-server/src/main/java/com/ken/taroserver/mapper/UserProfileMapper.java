package com.ken.taroserver.mapper;

import com.ken.taropojo.dto.UserDTO;
import com.ken.taropojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserProfileMapper {
    @Select("select * from users where user_id = #{id}")
    public UserDTO getByUserId(Long id);

    @Update("update users set username = #{username}, sex = #{sex}, age = #{age}, image_url = #{imageUrl} where user_id = #{userId}")
    public void updateUserProfile(UserDTO user);

    @Update("update users set image_url = #{imageUrl} where user_id = #{userId}")
    public void updateImage(UserDTO userProfileDTO);
}


