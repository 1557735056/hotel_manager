package com.poppy.service.impl;

import com.poppy.dao.UserMapper;
import com.poppy.entity.User;
import com.poppy.service.UserService;
import com.poppy.utils.PasswordUtil;
import com.poppy.utils.SystemConstant;
import com.poppy.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author poppy
 * @Date 2021/11/7 18:02
 * @Version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public int addUser(User user) {
        //自动生成盐值 hiro安全认证框架
        user.setSalt(UUIDUtils.randomUUID());
        //密码加密
        user.setPassword(PasswordUtil.md5(user.getPassword(),user.getSalt(), SystemConstant.PASSWORD_COUNT));
        return userMapper.addUser(user);
    }

    /**
     * 根据登录名查询名查询用户
     *
     * @param loginName
     * @return
     */
    @Override
    public User findUserByLoginName(String loginName) {
        return userMapper.findUserByLoginName(loginName);
    }

    /**
     * 登录
     *
     * @param loginName
     * @param password
     * @return
     */
    @Override
    public User login(String loginName, String password) {
        //调用查询用户信息的犯法
        User userByLoginName = userMapper.findUserByLoginName(loginName);
        if(userByLoginName != null){
            String newPassword = PasswordUtil.md5(password,userByLoginName.getSalt(),SystemConstant.PASSWORD_COUNT);
            if(newPassword.equals(userByLoginName.getLoginName()))
            {
                return userByLoginName;
            }
        }
        return null;
    }
}
