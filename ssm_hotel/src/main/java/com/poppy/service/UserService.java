package com.poppy.service;

import com.poppy.entity.User;

/**
 * @Author poppy
 * @Date 2021/11/7 18:02
 * @Version 1.0
 */
public interface UserService {
    /**
     * 添加用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 根据登录名查询名查询用户
     * @param loginName
     * @return
     */
    User findUserByLoginName(String loginName);

    /**
     * 登录
     * @param LoginName
     * @param password
     * @return
     */
    User login(String LoginName,String password);
}
