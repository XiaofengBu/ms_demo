package com.xiaofeng.ms.dao;

import com.xiaofeng.ms.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USER WHERE ID = #{id}")
    public User getUserById(@Param("id")int id);
    @Insert("INSERT INTO USER (ID,NAME) VALUES (#{id},#{name})")
    public int insertUser(User user);
}
