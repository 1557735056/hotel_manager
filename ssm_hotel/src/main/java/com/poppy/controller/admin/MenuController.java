package com.poppy.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.poppy.common.TreeNode;
import com.poppy.entity.Employee;
import com.poppy.entity.Menu;
import com.poppy.entity.MenuNode;
import com.poppy.entity.vo.MenuVo;
import com.poppy.service.MenuService;
import com.poppy.utils.DataGridViewResult;
import com.poppy.utils.SystemConstant;
import com.poppy.utils.TreeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author poppy
 * @Date 2021/11/3 8:24
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    @RequestMapping("/loadMenuList")
    public String loadMenuList(HttpSession session)
    {
        //创建map集合保存menuInfo菜单信息
        Map<String,Object> map = new HashMap<>(16);
        //创建map结合保存homeInfo信息
        Map<String,Object> homeInfo = new HashMap<>(16);
        //创建Map集合保存logoInfo信息
        Map<String,Object> logoInfo = new HashMap<>(16);
        //List<Menu> menuList = menuService.findMenuList();
        Employee employee = (Employee) session.getAttribute(SystemConstant.LOGINUSER);
        //根据当前用户的角色查询动态显示菜单列表
        List<Menu> menuList = menuService.findMenuListByEmployeeId(employee.getId());

        //创建集合保存菜单的关系
        List<MenuNode> menuInfo = new ArrayList<>();
        for (Menu menu : menuList) {
            //创建菜单节点
            MenuNode menuNode = new MenuNode();
            menuNode.setTitle(menu.getTitle());
            menuNode.setHref(menu.getHref());
            menuNode.setIcon(menu.getIcon());
            menuNode.setPid(menu.getPid());
            menuNode.setId(menu.getId());
            menuNode.setTarget(menu.getTarget());
            menuNode.setSpread(menu.getSpread());
            menuInfo.add(menuNode);
        }
        //保存homeInfo信息
        homeInfo.put("title","首页");
        homeInfo.put("href","/admin/home.html");

        //保存logoInfo信息
        logoInfo.put("title","酒店管理系统");
        logoInfo.put("image","/static/layui/images/logo.png");
        logoInfo.put("href","/admin/home.html");
        //将菜单信息添加到MenuInfo中
        map.put("homeInfo", homeInfo);
        map.put("logoInfo", logoInfo);
        map.put("menuInfo", TreeUtil.toTree(menuInfo,0));
        return JSON.toJSONString(map);
    }

    @PostMapping("/loadMenuTree")
    public DataGridViewResult loadMenuTree(){
        //调用查询所有菜单列表的方法
        List<Menu> menuList = menuService.findMenuList();
        //创建集合保存节点信息
        List<TreeNode> treeNodes = new ArrayList<>();
        //循环遍历菜单列表结合
        for (Menu menu : menuList) {
            //判断当前菜单是否展开
            boolean spread = (menu.getSpread() == null || menu.getSpread() == 1) ? true : false;
            //将菜单信息保存到treeNode集合中
            treeNodes.add(new TreeNode(menu.getId(),menu.getPid(),menu.getTitle(),spread));
        }
//        返回数据
        return new DataGridViewResult(treeNodes);
    }

    @GetMapping("/list")
    public DataGridViewResult list(MenuVo menuVo)
    {
        //设置分页信息
        PageHelper.startPage(menuVo.getPage(), menuVo.getLimit());
        //调用查询方法
        List<Menu> menuListByPage = menuService.findMenuListByPage(menuVo);
        //创建分页对象
        PageInfo<Menu> pageInfo = new PageInfo<Menu>();
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),menuListByPage);
    }

    @PostMapping("/addMenu")
    public String addMenu(Menu menu){
        Map<String,Object> map = new HashMap<>();
        if(menuService.addMenu(menu)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加失败");
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/updateMenu")
    public String updateMenu(Menu menu){
        Map<String,Object> map = new HashMap<>();
        if(menuService.updateMenu(menu)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"修改失败");
        }
        return JSON.toJSONString(map);
    }
    @GetMapping("/checkMenuHasChild")
    public String getMenuCountByMenuId(Integer id){
        Map<String,Object> map = new HashMap<>();
        if(menuService.getMenuCountByMenuId(id)>0){
            map.put(SystemConstant.EXISTS,true);
            map.put(SystemConstant.MESSAGE,"该菜单存在子菜单,无法删除");
        }else{
            map.put(SystemConstant.EXISTS,false);
        }
        return JSON.toJSONString(map);
    }
    @GetMapping("/deleteMenuById")
    public String deleteMenuById(Integer id){
        Map<String,Object> map = new HashMap<>();
        if(menuService.deleteById(id)>0){
            map.put(SystemConstant.SUCCESS,
                    true);
            map.put(SystemConstant.MESSAGE,"删除成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"删除失败");
        }
        return JSON.toJSONString(map);
    }
}
