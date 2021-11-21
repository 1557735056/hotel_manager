package com.poppy.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.poppy.entity.Employee;
import com.poppy.entity.dto.EmployeeDto;
import com.poppy.entity.vo.EmployeeVo;
import com.poppy.service.EmployeeService;
import com.poppy.utils.DataGridViewResult;
import com.poppy.utils.SystemConstant;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author poppy
 * @Date 2021/11/2 16:10
 * @Version 1.0
 */

@RestController
@RequestMapping("/admin/employee/")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;
    /**
     * 员工登录
     * @return
     */
    @PostMapping("/login")
    public String login(EmployeeDto employeeDto , HttpServletRequest request)
    {

        Map<String,Object> map = new HashMap<>(5);
        Employee employee = employeeService.login(employeeDto.getLoginName(), employeeDto.getLoginPwd());
        System.out.println(employee);
        if(employee != null){
            request.getSession().setAttribute(SystemConstant.LOGINUSER,employee);
            map.put(SystemConstant.SUCCESS,true);
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"账号密码错误,登录失败");
        }
        return JSON.toJSONString(map);
    }

    @GetMapping("/list")
    public DataGridViewResult list(EmployeeVo employeeVo)
    {
        //设置分页信息
        PageHelper.startPage(employeeVo.getPage(),employeeVo.getLimit());
        //调用查询的方法
        List<Employee> list = employeeService.findEmployeeList(employeeVo);
        //创建分页对象
        PageInfo<Employee> pageInfo = new PageInfo<>(list);
//        返回数据
        return new DataGridViewResult(pageInfo.getTotal(),list);
    }

    @PostMapping("/addEmployee")
    public String addEmployee(Employee employee,HttpSession httpsession){
        Map<String,Object> map = new HashMap<>(5);
        //获取当前登录用户
        Employee loginUser = (Employee) httpsession.getAttribute(SystemConstant.LOGINUSER);
        //设置创建人
        employee.setCreatedBy(loginUser.getId());
        //调用新增员工方法
        if(employeeService.addEmployee(employee)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加员工成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加员工失败");
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(Employee employee,HttpSession httpSession){
        Map<String,Object> map = new HashMap<>(5);
        //获取当前登录用户
        Employee loginUser = (Employee) httpSession.getAttribute(SystemConstant.LOGINUSER);
        //设置创建人
        employee.setModifyBy(loginUser.getId());

        //调用新增员工方法
        if(employeeService.updateEmployee(employee)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改员工成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"修改员工失败");
        }
        return JSON.toJSONString(map);
    }
    @GetMapping("/deleteEmployee")
    public String deleteEmployee(Integer id){
        Map<String,Object> map = new HashMap<>(5);
        //调用新增员工方法
        if(employeeService.deleteById(id)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"删除员工成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"删除员工失败");
        }
        return JSON.toJSONString(map);
    }

    /*** 重置密码 * @param id * @return */
    @PostMapping("/resetPwd")
    public String resetPwd(int id){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用重置密码的方法
        if(employeeService.resetPwd(id)>0){
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "密码重置成功");
        }else{
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "密码重置失败");
        }
        return JSON.toJSONString(map);
    }

    /*** 分配角色 * @param id * @return */
    @PostMapping("/saveEmployeeRole")
    public String saveEmployeeRole(String ids,int roleId){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用重置密码的方法
        if(employeeService.addEmployeeRole(ids,roleId)){
            map.put(SystemConstant.MESSAGE, "角色分配成功");
        }else{
            map.put(SystemConstant.MESSAGE, "角色分配失败失败");
        }
        return JSON.toJSONString(map);
    }

}
