package com.zh.bootStart.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.bootStart.dao.UserDao;
import com.zh.bootStart.bean.User;
import com.zh.bootStart.services.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserServiceI {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(String username, String password) {
        User user = new User(username,password);
        user = userDao.login(user);
        if(user == null){
            throw new RuntimeException("账号或密码有误！");
        }
        return user;
    }

    @Override
    public void updatePerson(String userJson, MultipartFile file) throws JsonProcessingException {
        User user = new ObjectMapper().readValue(userJson,User.class);
        int num = userDao.updatePerson(user);
        if(num == 0){
            throw new RuntimeException("修改个人信息失败！");
        }
    }
}
