package com.poppy.service.impl;

import com.poppy.dao.MenuMapper;
import com.poppy.entity.Menu;
import com.poppy.entity.vo.MenuVo;
import com.poppy.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author poppy
 * @Date 2021/11/3 10:03
 * @Version 1.0
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {


        @Resource
        private MenuMapper menuMapper;
        @Override
        public List<Menu> findMenuList() {
            return menuMapper.findMenuList();
        }

        /**
         * 根据角色id查询菜单id
         *
         * @param roleId
         * @return
         */
        @Override
        public List<Integer> findMenuIdListByRoleId(int roleId) {
                return menuMapper.findMenuIdListByRoleId(roleId);
        }

        /**
         * 根据menuid集合查询menu
         *
         * @param currentRoleMenuIds
         * @return
         */
        @Override
        public List<Menu> findMenuByMenuId(List<Integer> currentRoleMenuIds) {
                return menuMapper.findMenuByMenuId(currentRoleMenuIds);
        }

        /**
         * 查询菜单列表
         *
         * @param menuVo
         * @return
         */
        @Override
        public List<Menu> findMenuListByPage(MenuVo menuVo) {
                return menuMapper.findMenuListByPage(menuVo);
        }

        /**
         * 添加菜单
         *
         * @param menu
         * @return
         */
        @Override
        public int addMenu(Menu menu) {
                return menuMapper.addMenu(menu);
        }

        /**
         * 修改菜单
         *
         * @param menu
         * @return
         */
        @Override
        public int updateMenu(Menu menu) {
                return menuMapper.updateMenu(menu);
        }

        /**
         * 根据菜单id查询该菜单是否存在子id
         *
         * @param id
         * @return
         */
        @Override
        public int getMenuCountByMenuId(Integer id) {
                return menuMapper.getMenuCountByMenuId(id);
        }

        /**
         * 删除菜单
         *
         * @param id
         * @return
         */
        @Override
        public int deleteById(int id) {
                return menuMapper.deleteById(id);
        }

        /**
         * 根据当前登录员工的角色查询菜单列表
         *
         * @param employeeId
         * @return
         */
        @Override
        public List<Menu> findMenuListByEmployeeId(Integer employeeId) {
                return menuMapper.findMenuListByEmployeeId(employeeId);
        }

}
