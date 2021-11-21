package com.poppy.dao;

import com.poppy.entity.Employee;
import com.poppy.entity.vo.EmployeeVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author poppy
 * @Date 2021/11/2 16:11
 * @Version 1.0
 */

public interface EmployeeMapper {
    /**
     * 根据登录账号查询员工信息
     * @param loginName
     * @return
     */
    Employee findEmployeeByLoginName(String loginName);

    /**
     * 根据部门编号查询该部门的下的员工数量
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

    /**
     *根据id删除角色用户数据
     * @param id
     * @return
     */
    @Delete("delete from sys_role_employee where eid = #{id}")
    int deleteRoleAndEmployee(@Param("id") Integer id);


    @Insert("insert into sys_role_employee(eid,rid)values(#{eid},#{rid})")
    void addEmployeeRole(@Param("rid") String roleId,@Param("eid") Integer empId);

    /**
     * 删除原来角色和员工的关系
     * @param empId
     */
    @Delete("delete from sys_role_employee where eid = #{empId}")
    void deleteEmployeeAndRole(Integer empId);
}
