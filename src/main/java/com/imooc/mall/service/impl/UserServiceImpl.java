package com.imooc.mall.service.impl;

import com.imooc.mall.dao.UserMapper;
import com.imooc.mall.enums.ResponseEnum;
import com.imooc.mall.enums.RoleEnum;
import com.imooc.mall.pojo.User;
import com.imooc.mall.service.IUserService;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

import static com.imooc.mall.enums.ResponseEnum.*;

@Service
public  class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public ResponseVo<User> register(User user) {
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if (countByUsername > 0){
           // throw new RuntimeException("该username已被注册");
           return ResponseVo.error(USERNAME_EXISE);
        }
        int countByEmail = userMapper.countByEmail(user.getEmail());
        if (countByEmail > 0){
            //throw new RuntimeException("该email已被注册");
            return ResponseVo.error(EMAIL_EXISTS);
        }
        user.setRole(RoleEnum.CUSTOMER.getCode());

        //md5摘要算法（spring自带）
        user.setPassword(DigestUtils.md5DigestAsHex(
                user.getPassword().getBytes(StandardCharsets.UTF_8)
        ));
        int resultCount = userMapper.insertSelective(user);
        if (resultCount == 0){
            //throw new RuntimeException("注册失败");
            return ResponseVo.error(ERROR);
        }

        return ResponseVo.success();
    }

    @Override
    public ResponseVo<User> login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null){
            return ResponseVo.error(USERNAME_OR_PASSWORD_ERROR);
        }
        if (!user.getPassword().equalsIgnoreCase(DigestUtils.md5DigestAsHex(
                password.getBytes(StandardCharsets.UTF_8)
                ))){
            return ResponseVo.error(USERNAME_OR_PASSWORD_ERROR);
        }

        user.setPassword("");
        return ResponseVo.success(user);
    }
}
