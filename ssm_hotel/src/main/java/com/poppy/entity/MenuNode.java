package com.poppy.entity;

import java.util.List;

/**
 * 菜单节点工具类
 */
public class MenuNode {
    /**
     *  菜单编号
     */
    private Integer id;
    //父级菜单编号
    private Integer pid;
    //菜单名称
    private String title;
    //链接地址
    private String href;
    //是否展开
    private Integer spread;
    //打开方式
    private String target;
    //菜单图标
    private String icon;
    //子菜单集合列表
    private List<MenuNode> child;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getSpread() {
        return spread;
    }

    public void setSpread(Integer spread) {
        this.spread = spread;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<MenuNode> getChild() {
        return child;
    }

    public void setChild(List<MenuNode> child) {
        this.child = child;
    }
}
