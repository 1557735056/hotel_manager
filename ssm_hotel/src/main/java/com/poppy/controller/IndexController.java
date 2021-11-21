package com.poppy.controller;

import com.poppy.entity.Floor;
import com.poppy.entity.Room;
import com.poppy.entity.RoomType;
import com.poppy.service.FloorService;
import com.poppy.service.RoomService;
import com.poppy.service.RoomTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author poppy
 * @Date 2021/11/7 19:46
 * @Version 1.0
 */
@Controller
public class IndexController {
    @Resource
    private RoomTypeService roomTypeService;
    @Resource
    private RoomService roomService;
    @Resource
    private FloorService floorService;

    @RequestMapping("/index.html")
    public String index(Model model, HttpServletRequest request,HttpServletResponse response)
    {
        //调用查询房型的方法
        List<RoomType> roomTypeList = roomTypeService.getAllRoomType(null);
        //调用查询所有楼层列白的方法
        List<Floor> flowList = floorService.findFlowList(null);
        ArrayList

        List<Room> roomList = roomService.findRoomByFloorId();
        model.addAttribute("roomList",roomList);
        //将数据放到模型中去
        model.addAttribute("roomTypeList",roomTypeList);
        model.addAttribute("floorList",flowList);
        return "forward:/home.jsp";
    }


}
