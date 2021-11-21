package com.poppy.entity.vo;

import com.poppy.entity.Dept;
import com.poppy.entity.Floor;

/**
 * @Author poppy
 * @Date 2021/11/3 9:47
 * @Version 1.0
 */
public class FloorVo extends Floor {
    /**
     * 当前页面
     */
    private Integer page;

    /**
     * 每页显示数据
     */
    private Integer limit;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
