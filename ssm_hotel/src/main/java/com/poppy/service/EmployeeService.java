package com.poppy.service;

import com.poppy.entity.Employee;
import com.poppy.entity.vo.EmployeeVo;

import java.util.List;

/**
 * @Author poppy
 * @Date 2021/11/2 16:11
 * @Version 1.0
 */

public interface EmployeeService {
    /**
     * 员工登录
     *
     * @param loginName
     * @param password
     * @return
     */
    Employee login(String loginName, String password);


    /**
     * 根据部门编号查询该部门的下的员工数量
     *
     * @param deptId
     * @return
     */
    int getEmployeeByDept(Integer deptId);

    /**
     * 根据roleId查询员工数量
     * @param roleId
     * @return
     */
    int getEmployeeCountByRoleId(Integer roleId);

    /**
     * 查询员工列表
     * @param employeeVo
     * @return
     */
    List<Employee> findEmployeeList(EmployeeVo employeeVo);

    /**
     * 添加用户
     * @param employee
     * @return
     */
    int addEmployee(Employee employee);

    /**
     * 修改用户信息
     * @param employee
     * @return
     */
    int updateEmployee(Employee employee);

    /**
     * 删除用户
     * @param employeeId
     * @return
     */
    int deleteById(Integer employeeId);

    /*** 重置密码 * @param id * @return */
    int resetPwd(int id);

    /**
     * 保存员工角色信息
     * @param roleId
     * @param empId
     */
    boolean addEmployeeRole(String roleId,Integer empId);

}

