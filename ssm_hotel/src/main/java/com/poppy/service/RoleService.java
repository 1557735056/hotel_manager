package com.poppy.service;

import com.poppy.entity.Role;
import com.poppy.entity.vo.RoleVo;

import java.util.List;
import java.util.Map;

/**
 * @Author poppy
 * @Date 2021/11/3 15:25
 * @Version 1.0
 */
public interface RoleService {
    /**
     * 获取role列表
     * @param roleVo
     * @return
     */
    List<Role> findRoleList(RoleVo roleVo);

    /**
     * 添加角色
     * @param Role
     * @return
     */
    int addRole(Role Role);


    /**
     * 修改角色
     * @param role
     * @return
     */
    int updateRole(Role role);

    /**
     * 删除角色
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     *
     * @param ids
     * @param RoleId
     * @return
     */
    int  saveRoleMenu(String ids, Integer RoleId);

    /**
     * 查询所有角色列表
     * @return
     */
    List<Map<String,Object>> findRoleListByMap();

    /**
     * 根据员工id查询该员工拥有的角色列表
     * @param employeeId
     * @return
     */
    List<Integer> findEmployeeRoleByEmployeeId(Integer employeeId);

}
