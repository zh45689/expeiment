package com.zh.bootStart.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zh.bootStart.bean.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserServiceI {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public User login(String username,String password);

    /**
     * 修改个人信息
     * @param userJson
     */
    public void updatePerson(String userJson, MultipartFile file) throws JsonProcessingException;
}
