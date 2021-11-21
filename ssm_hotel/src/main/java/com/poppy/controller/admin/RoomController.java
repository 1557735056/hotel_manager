package com.poppy.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.poppy.entity.Room;
import com.poppy.entity.RoomType;
import com.poppy.entity.vo.RoomVo;
import com.poppy.service.RoomService;
import com.poppy.service.RoomTypeService;
import com.poppy.utils.DataGridViewResult;
import com.poppy.utils.SystemConstant;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author poppy
 * @Date 2021/11/7 14:50
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/room")
public class RoomController {


    @Resource
    private RoomService roomService;
    @Resource
    private RoomTypeService roomTypeService;
    @RequestMapping("/list")
    public DataGridViewResult list(RoomVo roomVo)
    {
        PageHelper.startPage(roomVo.getPage(), roomVo.getLimit());
        List<Room> list = roomService.findRoomListByPage(roomVo);
        PageInfo<Room> pageInfo = new PageInfo<>(list);
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @PostMapping("/addRoom")
    public String addRoom(Room room)
    {
        Map<String,Object> map = new HashMap<>();
        if(roomService.addRoom(room)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加失败");
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/updateRoom")
    public String updateRoom(Room room)
    {
        Map<String,Object> map = new HashMap<>();
        if(roomService.updateRoom(room)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"修改失败");
        }
        return JSON.toJSONString(map);
    }
    @PostMapping("/deleteRoom")
    public String addRoom(Integer id)
    {
        Map<String,Object> map = new HashMap<>();
        if(roomService.deleteRoom(id)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"删除成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"删除失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 根据房型查询房间
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/list/{id}")
    public String list(@PathVariable("id")Integer id, Model model)
    {
        //调用查询所有房型列表的方法
        List<RoomType> roomTypeList = roomTypeService.getAllRoomType(null);
        RoomVo roomVo = new RoomVo();
        roomVo.setRoomtypeid(id);
        roomVo.setStatus(3);
        //查询房间列表
        List<Room> roomList = roomService.findRoomListByPage(roomVo);
        model.addAttribute("roomTypeList",roomTypeList);
        model.addAttribute("roomList",roomList);
        model.addAttribute("typeId",id);
        return "hotelList";
    }
}
