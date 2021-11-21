package com.poppy.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.poppy.entity.vo.DeptVo;
import com.poppy.entity.Dept;
import com.poppy.service.DeptService;
import com.poppy.service.EmployeeService;
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
 * @Date 2021/11/3 10:04
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/dept")
public class DeptController {
    @Resource
    private DeptService deptService;
    @Resource
    private EmployeeService employeeService;

    /**
     *  查询部门列表
     */
    @RequestMapping("/list")
    public DataGridViewResult list(DeptVo deptVo){
        //设置分页新消息
        PageHelper.startPage(deptVo.getPage(), deptVo.getLimit());
        //调用分页查询的方法
        List<Dept> list = deptService.findDeptListByPage(deptVo);
        //创建分页对象
        PageInfo<Dept> pageInfo = new PageInfo<>(list);
        return new DataGridViewResult(pageInfo.getTotal(),list);
    }

    @PostMapping("/addDept")
    public  String addDept(Dept dept){

        Map<String,Object> map = new HashMap<>();
        if(deptService.addDept(dept)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加失败");
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/updateDept")
    public  String updateDept(Dept dept){

        Map<String,Object> map = new HashMap<>();
        if(deptService.updateDept(dept)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"修改失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 检查该部门下是否还有员工信息
     * @param id
     * @return
     */
    @RequestMapping("/checkDeptHasEmployee")
    public String checkDeptHasEmployee(Integer id){
        Map<String,Object> map = new HashMap<>();
        //更具
        if(employeeService.getEmployeeByDept(id)>0){
            map.put(SystemConstant.EXISTS,true);
            map.put(SystemConstant.MESSAGE,"该部门存在员工信息 无法删除");
        }else{
            map.put(SystemConstant.EXISTS,false);
        }
        return JSON.toJSONString(map);
    }
    /**
     * 删除部门
     */
    @RequestMapping("/deleteById")
    public String deleteById(Integer id){
        Map<String,Object> map = new HashMap<>();
        //更具
        if(deptService.deleteById(id)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"删除成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"删除失败");
        }
        return JSON.toJSONString(map);
    }

    @GetMapping("/deptList")
    public String deptList()
    {
        return JSON.toJSONString(deptService.findDeptList());
    }
}
