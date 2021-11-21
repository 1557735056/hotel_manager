package com.poppy.controller;

import com.poppy.entity.Room;
import com.poppy.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @Author poppy
 * @Date 2021/11/7 18:34
 * @Version 1.0
 */
@Controller
public class FrontPageController {

    @Resource
    private RoomService roomService;
    @RequestMapping("/user/register.html")
    public String register(){
        return "/register";
    }

    @RequestMapping("/user/login.html")
    public String login(){
        return "/login";
    }

    @RequestMapping("/room/${id}.html")
    public String room(@PathVariable("id") Integer id, Model model){
        Room room = roomService.findById(id);
        model.addAttribute("room",room);
        return "detail";
    }


}
