package com.poppy.dao;

import com.poppy.entity.Role;
import com.poppy.entity.vo.RoleVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author poppy
 * @Date 2021/11/3 15:19
 * @Version 1.0
 */
public interface RoleMapper {
    /**
     * 获得角色
     * @param roleVo
     * @return
     */
    List<Role> findRoleList(RoleVo roleVo);

    /**
     * 添加角色
     * @param role
     * @return
     */
    int addRole(Role role);

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
     * @param menuId
     * @param roleId
     */
    @Insert("insert into sys_role_menu(mid,rid) values (#{menuId},#{roleId})")
     int addRoleMenu(@Param("menuId") String menuId, @Param("roleId")Integer roleId);

    /**
     * 删除之前所拥有的的role和菜单数据
     * @param roleId
     */
    @Delete("delete from sys_role_menu where rid = #{roleId}")
    void deleteRoleMenu(Integer roleId);

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
