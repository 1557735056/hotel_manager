package com.poppy.entity.vo;

import com.poppy.entity.Employee;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author poppy
 * @Date 2021/11/4 10:55
 * @Version 1.0
 */
public class EmployeeVo extends Employee {

    /**
     * 当前页面
     */
    private Integer page;

    /**
     * 每页显示数据
     */
    private Integer limit;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
