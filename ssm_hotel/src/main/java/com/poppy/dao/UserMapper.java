package com.poppy.dao;

import com.poppy.entity.User;

/**
 * @Author poppy
 * @Date 2021/11/7 17:37
 * @Version 1.0
 */
public interface UserMapper {

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
     * @param loginName
     * @param password
     * @return
     */
    User login(String loginName,String password);
}
