package com.xiaofeng.ms.service;

import com.xiaofeng.ms.dao.UserMapper;
import com.xiaofeng.ms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserById(int id){
        return userMapper.getUserById(id);
    }

    @Transactional
    public int getTran(){
        User user = new User();
        user.setId(2);
        user.setName("test2");

        User user1 = new User();
        user1.setId(1);
        user1.setName("test");
        userMapper.insertUser(user);
        return userMapper.insertUser(user1);
    }
}
