package com.poppy.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.poppy.common.TreeNode;
import com.poppy.entity.Dept;
import com.poppy.entity.Menu;
import com.poppy.entity.Role;
import com.poppy.entity.vo.RoleVo;
import com.poppy.service.EmployeeService;
import com.poppy.service.MenuService;
import com.poppy.service.RoleService;
import com.poppy.utils.DataGridViewResult;
import com.poppy.utils.SystemConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author poppy
 * @Date 2021/11/3 15:27
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private MenuService menuService;
    @RequestMapping("/list")
    public DataGridViewResult list(RoleVo roleVo)
    {
        //设置分页新消息
        PageHelper.startPage(roleVo.getPage(), roleVo.getLimit());
        //调用分页查询的方法
        List<Role> list = roleService.findRoleList(roleVo);
        //创建分页对象
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        // pageInfo.getTotal() 总数量 ,list 列表
        return new DataGridViewResult(pageInfo.getTotal(),list);
    }
    @PostMapping("/addRole")
    public String addRole(Role role)
    {
        Map<String,Object> map = new HashMap<>();
        if(roleService.addRole(role)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加失败");
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/updateRole")
    public String updateRole(Role role)
    {
        Map<String,Object> map = new HashMap<>();
        if(roleService.updateRole(role)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"修改失败");
        }
        return JSON.toJSONString(map);
    }
    /**
     * 检查该角色下是否还有员工信息
     * @param id
     * @return
     */
    @RequestMapping("/checkRoleHasEmployee")
    public String checkRoleHasEmployee(Integer id){
        Map<String,Object> map = new HashMap<>();
        if(employeeService.getEmployeeCountByRoleId(id)>0){
            map.put(SystemConstant.EXISTS,true);
            map.put(SystemConstant.MESSAGE,"该角色存在员工信息 无法删除");
        }else{
            map.put(SystemConstant.EXISTS,false);
        }
        return JSON.toJSONString(map);
    }

    /**
     * 删除角色信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public String deleteById(Integer id){
        Map<String,Object> map = new HashMap<>(15);
        if(roleService.deleteById(id)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"删除成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"删除失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     *
     *
     * 初始化菜单树
     * @return
     */
    @PostMapping("/initMenuTree")
    public DataGridViewResult initMenuTree(int roleId)
    {
        //调用查询菜单列表的方法
        List<Menu> menus = menuService.findMenuList();
        //根据role获取到sys_menu_role表中的roleId
        List<Integer> menuIds = menuService.findMenuIdListByRoleId(roleId);
        //根据menuIds获取到当前角色拥有的菜单
        List<Menu> currentMenus = new ArrayList<>();
        //查询集合中是否存在数据
        if(menuIds!=null && menuIds.size()>0)
        {
            currentMenus = menuService.findMenuByMenuId(menuIds);
        }

        //创建集合保存节点信息
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Menu menu : menus) {
            //定义变量 0表示不选中
            String checkArr = "0";
            //内层循环遍历当前角色拥有的权限菜单
            //相同则表示该角色拥有该菜单的权限
            for (Menu currentMenu : currentMenus) {
                if (currentMenu.getId().equals(menu.getId())) {
                    checkArr = "1";
                    break;
                }
            }
            //定义变量
            boolean spread = (menu.getSpread() == null || menu.getSpread() == 1) ? true : false;
            treeNodes.add(new TreeNode(menu.getId(), menu.getPid(), menu.getTitle(), spread,checkArr));
        }
        return new DataGridViewResult(treeNodes);
    }

    @PostMapping("/saveRoleMenu")
    public String saveRoleMenu(String ids,Integer roleId)
    {
        Map<String,Object> map = new HashMap<>();
        if(roleService.saveRoleMenu(ids,roleId) >0){
            map.put(SystemConstant.MESSAGE,"菜单分配成功");
        }else{
            map.put(SystemConstant.MESSAGE,"菜单分配失败");
        }
        return JSON.toJSONString(map);
    }


    @GetMapping("/initRoleListByEmpId")
    public DataGridViewResult initRoleListByEmpId(int id)
    {
        //调用查询所有角色列表的方法
        List<Map<String, Object>> roleList = roleService.findRoleListByMap();
        //根据员工id来获取该员工所拥有的角色列表得方法
        List<Integer> roleIdList = roleService.findEmployeeRoleByEmployeeId(id);
        //比价角色id值是否相等
        for (Map<String, Object> map : roleList) {
            boolean flag = false;
            //获取每个角色的id
            Integer rid = (Integer) map.get("id");
            //内层循环遍历该员工拥有的角色列表
            for (Integer integer : roleIdList) {
                if(rid.equals(integer)){
                    flag = true;
                    break;
                }
            }
            map.put("LAY_CHECKED",flag);
        }
        return new DataGridViewResult(Long.valueOf(roleIdList.size()),roleList);
    }

}
