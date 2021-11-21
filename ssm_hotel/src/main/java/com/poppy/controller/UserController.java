package com.poppy.controller;

import com.alibaba.fastjson.JSON;
import com.poppy.entity.User;
import com.poppy.service.UserService;
import com.poppy.utils.SystemConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author poppy
 * @Date 2021/11/7 17:45
 * @Version 1.0
 */

@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserService userService;

    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("/register")
    public String  register(User user)
    {
        Map<String,Object> map = new HashMap<>();
        if(userService.addUser(user)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"注册成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"很遗憾,注册失败");
        }
        return JSON.toJSONString(map);
    }


    @GetMapping("/checkName")
    public String checkName(String loginName)
    {
        Map<String,Object> map = new HashMap<>();
        if(userService.findUserByLoginName(loginName)!=null){
            map.put(SystemConstant.EXISTS,true);
            map.put(SystemConstant.MESSAGE,"用户已经存在,请重新输入");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"很遗憾,注册失败");
        }
        return JSON.toJSONString(map);
    }

    @GetMapping("/login")
    public String login(String loginName, String password, HttpSession session)
    {
        Map<String,Object> map = new HashMap<>();
        User user = userService.login(loginName, password);
        if(user != null){
            map.put(SystemConstant.SUCCESS,true);
            user.setPassword(null);
            session.setAttribute(SystemConstant.LOGINUSER,user);
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"账号或密码错误,请重新输入");
        }
        return JSON.toJSONString(map);
    }
}
