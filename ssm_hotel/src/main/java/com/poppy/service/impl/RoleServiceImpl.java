package com.poppy.service.impl;

import com.poppy.dao.RoleMapper;
import com.poppy.entity.Role;
import com.poppy.entity.vo.RoleVo;
import com.poppy.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author poppy
 * @Date 2021/11/3 15:26
 * @Version 1.0
 */
@Service
@Transactional
public class RoleServiceImpl  implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    /**
     * 获取role列表
     *
     * @param roleVo
     * @return
     */
    @Override
    public List<Role> findRoleList(RoleVo roleVo) {
        return roleMapper.findRoleList(roleVo);
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @Override
    public int addRole(Role role) {
        return roleMapper.addRole(role);
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) {
        return roleMapper.deleteById(id);
    }

    /**
     * @param ids
     * @param roleId
     * @return
     */
    @Override
    public int saveRoleMenu(String ids, Integer roleId) {
        try{
            //删除原有的菜单关系
            roleMapper.deleteRoleMenu(roleId);
            //将ids拆分成数组
            String[] str = ids.split(",");
            //循环调用
            for (int i = 0; i < str.length; i++) {
                //嗲用保存菜单角色关系的方法
                roleMapper.addRoleMenu(str[i], roleId);
            }
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 查询所有角色列表
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> findRoleListByMap() {
        return roleMapper.findRoleListByMap();
    }

    /**
     * 根据员工id查询该员工拥有的角色列表
     *
     * @param employeeId
     * @return
     */
    @Override
    public List<Integer> findEmployeeRoleByEmployeeId(Integer employeeId) {
        return roleMapper.findEmployeeRoleByEmployeeId(employeeId);
    }
}
