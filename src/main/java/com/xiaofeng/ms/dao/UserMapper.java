package com.xiaofeng.ms.dao;

import com.xiaofeng.ms.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USER WHERE ID = #{id}")
    public User getUserById(@Param("id")Long id);
    @Insert("INSERT INTO USER (ID,NAME) VALUES (#{id},#{name})")
    public int insertUser(User user);

    @Update("update USER set password = #{password} where id = #{id}")
    public void update(User toBeUpdate);
}
