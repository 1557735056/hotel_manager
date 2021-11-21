package com.poppy.controller.admin;

import com.poppy.utils.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author poppy
 * @Date 2021/11/2 15:28
 * @Version 1.0
 * 负责跳转页面
 */
@Controller
@RequestMapping("/admin")
public class PageController {

    /**
     *去到登录页面
     * @return
     */
    @RequestMapping("/login.html")
    public String login(){
        return "admin/login";
    }

    @RequestMapping("/home.html")
    public String home(){
        return "admin/home";
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //清空session
        session.removeAttribute(SystemConstant.LOGINUSER);
        //重定向到登录页面
        return "redirect:/admin/login.html";
    }

    /**
     * 去到部门管理页面
     * @return
     */
    @RequestMapping("/toDeptManager")
    public String toDeptManager()
    {
        return "admin/dept/deptManager";
    }
    /**
     * 去到角色管理页面
     * @return
     */
    @RequestMapping("/toRoleManager")
    public String toRoleManager()
    {
        return "admin/role/roleManager";
    }

    /**
     * 去到角色管理页面
     * @return
     */
    @RequestMapping("/toEmployeeManager")
    public String toEmployeeManager()
    {
        return "admin/employee/employeeManager";
    }

    @RequestMapping("/toMenuManager")
    public String roMenuManager(){
        return "admin/menu/menuManager";
    }

    @RequestMapping("/toFloorManager")
    public String toFloorManager(){
        return "admin/floor/floorManager";
    }

    @RequestMapping("/toRoomTypeManager")
    public String toRoomTypeManager(){
        return "admin/roomType/roomTypeManager";
    }

    @RequestMapping("/toRoomManager")
    public String toRoomManager(){
        return "admin/room/roomManager";
    }
}


