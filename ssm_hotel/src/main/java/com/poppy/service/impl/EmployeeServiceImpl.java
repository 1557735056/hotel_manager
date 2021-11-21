package com.poppy.service.impl;

import com.poppy.dao.EmployeeMapper;
import com.poppy.dao.MenuMapper;
import com.poppy.entity.Employee;
import com.poppy.entity.Menu;
import com.poppy.entity.vo.EmployeeVo;
import com.poppy.service.DeptService;
import com.poppy.service.EmployeeService;
import com.poppy.service.MenuService;
import com.poppy.utils.PasswordUtil;
import com.poppy.utils.SystemConstant;
import com.poppy.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.util.Password;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author poppy
 * @Date 2021/11/2 16:16
 * @Version 1.0
 */
@Service
@Transactional

public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;


    @Resource
    private DeptService deptService;
    /**
     * 员工登录
     * @param loginName
     * @param password
     * @return
     */
    @Override
    public Employee login(String loginName, String password) {
        Employee employee = employeeMapper.findEmployeeByLoginName(loginName);
        if(employee!= null){
            //将密码加密处理
            String newPassword = PasswordUtil.md5(password,employee.getSalt(), SystemConstant.PASSWORD_COUNT);
            //比较密码是否一直
            if(employee.getLoginPwd().equals(newPassword)){
                //登录成功
                return employee;
            }
        }
        return null;
    }

    @Override
    public int getEmployeeByDept(Integer deptId) {
        return employeeMapper.getEmployeeByDept(deptId);
    }

    /**
     * 根据roleId查询员工数量
     *
     * @param roleId
     * @return
     */
    @Override
    public int getEmployeeCountByRoleId(Integer roleId) {
        return employeeMapper.getEmployeeCountByRoleId(roleId);
    }

    /**
     * 查询员工列表
     *
     * @param employeeVo
     * @return
     */
    @Override
    public List<Employee> findEmployeeList(EmployeeVo employeeVo) {
        return employeeMapper.findEmployeeList(employeeVo);
    }

    /**
     * 添加用户
     *
     * @param employee
     * @return
     */
    @Override
    public int addEmployee(Employee employee) {
        employee.setSalt(UUIDUtils.randomUUID());
        employee.setCreateDate(new Date());
        employee.setLoginPwd(PasswordUtil.md5(SystemConstant.DEFAULT_PASSWORD,employee.getSalt(),SystemConstant.PASSWORD_COUNT));
        return employeeMapper.addEmployee(employee);
    }

    /**
     * 修改用户信息
     *
     * @param employee
     * @return
     */
    @Override
    public int updateEmployee(Employee employee) {
        employee.setCreateDate(new Date());
        return employeeMapper.updateEmployee(employee);
    }

    /**
     * 删除用户
     *
     * @param employeeId
     * @return
     */
    @Override
    public int deleteById(Integer employeeId) {
        employeeMapper.deleteRoleAndEmployee(employeeId);
        return employeeMapper.deleteById(employeeId);
    }

    /*** 重置密码 * @param id * @return
     * @param id*/
    @Override
    public int resetPwd(int id) {
        Employee employee = new Employee();
        //必须先设置盐值,再给密码重新加密复制
        employee.setSalt(UUIDUtils.randomUUID());
        employee.setLoginPwd(PasswordUtil.md5(SystemConstant.DEFAULT_PASSWORD,employee.getSalt(),SystemConstant.PASSWORD_COUNT));
        employee.setId(id);
        return employeeMapper.updateEmployee(employee);
    }

    /**
     * 保存员工角色信息
     *
     * @param roleId
     * @param empId
     */
    @Override
    public boolean addEmployeeRole(String roleId, Integer empId) {
        try{
//            删除员工角色关系表的数据
            employeeMapper.deleteEmployeeAndRole(empId);
            //在保存员工角色关系
            String[] str  = roleId.split(",");
            for (int i = 0; i < str.length; i++) {
                employeeMapper.addEmployeeRole(str[i],empId);
            }
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }


}
