package com.poppy.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.poppy.entity.Floor;
import com.poppy.entity.vo.FloorVo;
import com.poppy.service.FloorService;
import com.poppy.utils.DataGridViewResult;
import com.poppy.utils.SystemConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author poppy
 * @Date 2021/11/5 13:51
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/floor")
public class FloorController {

    @Resource
    private FloorService floorService;

    @GetMapping("/list")
    public DataGridViewResult list(FloorVo floorVo){
        //设置分页信息
        PageHelper.startPage(floorVo.getPage(),floorVo.getLimit());
        //调用查询楼层列表的方法
        List<Floor> floorList = floorService.findFlowList(floorVo);
        //创建分页对象
        PageInfo<Floor> pageInfo = new PageInfo<>(floorList);
        return new DataGridViewResult(pageInfo.getTotal(),floorList);
    }

    @PostMapping("/addFloor")
    public String addFloor(Floor floor){

        Map<String,Object> map = new HashMap<>();
        if(floorService.addFloor(floor)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加失败");
        }
        return JSON.toJSONString(map);

    }

    @PostMapping("/updateFloor")
    public String updateFloor(Floor floor){
        Map<String,Object> map = new HashMap<>();
        if(floorService.updateFloor(floor)>0){
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
        if(floorService.deleteById(id)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"删除成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"删除失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 查询所有楼层
     * @return
     */

    @GetMapping("/findAll")
    public String findAll(){
        return JSON.toJSONString(floorService.findFlowList(null));
    }
}
