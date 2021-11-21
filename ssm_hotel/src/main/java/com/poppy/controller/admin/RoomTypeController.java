package com.poppy.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.poppy.entity.Floor;
import com.poppy.entity.RoomType;
import com.poppy.entity.vo.RoomTypeVo;
import com.poppy.service.RoomTypeService;
import com.poppy.utils.DataGridViewResult;
import com.poppy.utils.SystemConstant;
import com.poppy.utils.UUIDUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author poppy
 * @Date 2021/11/5 14:48
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/roomType")
public class RoomTypeController {

    @Resource
    private RoomTypeService roomTypeService;
    @GetMapping("/list")
    public DataGridViewResult list(RoomTypeVo roomTypeVo){
        PageHelper.startPage(roomTypeVo.getPage(),roomTypeVo.getLimit());
        List<RoomType> roomTypeList = roomTypeService.getAllRoomType(roomTypeVo);
        PageInfo<RoomType> pageInfo = new PageInfo<>(roomTypeList);
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }


    @PostMapping("/addRoomType")
    public String addRoomType(RoomType RoomType){

        Map<String,Object> map = new HashMap<>();
        if(roomTypeService.addRoomType(RoomType)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加失败");
        }
        return JSON.toJSONString(map);

    }

    @PostMapping("/updateRoomType")
    public String updateRoomType(RoomType roomType){
        Map<String,Object> map = new HashMap<>();
        if(roomTypeService.updateRoomType(roomType)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"修改失败");
        }
        return JSON.toJSONString(map);
    }

    @GetMapping("/deleteById")
    public String deleteById(Integer id){
        Map<String,Object> map = new HashMap<>();
        if(roomTypeService.deleteRoomTypeById(id)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"删除成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"删除失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 查询所有房型
     * @return
     */
    @GetMapping("/findAll")
    public String findAll(){
        return JSON.toJSONString(roomTypeService.getAllRoomType(null));
    }
}
