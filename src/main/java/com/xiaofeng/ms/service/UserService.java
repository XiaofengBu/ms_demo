package com.xiaofeng.ms.service;

import com.sun.deploy.net.proxy.pac.PACFunctions;
import com.sun.org.apache.bcel.internal.classfile.Code;
import com.xiaofeng.ms.dao.UserMapper;
import com.xiaofeng.ms.exception.GlobalException;
import com.xiaofeng.ms.exception.LoginException;
import com.xiaofeng.ms.exception.MobileNotExistException;
import com.xiaofeng.ms.exception.ServerException;
import com.xiaofeng.ms.model.User;
import com.xiaofeng.ms.result.CodeMsg;
import com.xiaofeng.ms.util.MD5Util;
import com.xiaofeng.ms.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserById(Long id){
        return userMapper.getUserById(id);
    }

    public Boolean login(LoginVO loginVO) throws GlobalException{
        if(loginVO == null || loginVO.getPassword() == null || loginVO.getMobile() == null){
            throw new ServerException();
        }
        String moblie = loginVO.getMobile();
        String password = loginVO.getPassword();
        User user = getUserById(Long.parseLong(moblie));
        if(user == null){
            throw new MobileNotExistException();
        }
        String dbPass = user.getPassword();
        String dbSalt = user.getSalt();
        String calcPass = MD5Util.tranPass2DBPass(password,dbSalt);
        if(dbPass.equals(calcPass)){
            return true;
        }else{
            throw new LoginException();
        }
    }

}
