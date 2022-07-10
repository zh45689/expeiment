package com.zh.bootStart.dao;

import com.zh.bootStart.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    /**
     * 登录
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 修改个人信息
     * @param user
     * @return
     */
    public int updatePerson(User user);
}
