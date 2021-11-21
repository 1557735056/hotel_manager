package com.poppy.dao;

import com.poppy.entity.Dept;
import com.poppy.entity.vo.DeptVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author poppy
 * @Date 2021/11/3 9:45
 * @Version 1.0
 */
public interface DeptMapper {
    /**
     *查询所有部门信息
     * @param deptVo
     * @return
     */
    List<Dept> findDeptListByPage(DeptVo deptVo);


    /**
     * 添加部门
     * @param dept
     * @return
     */
    int addDept(Dept dept);

    /**
     * 修改部门
     * @param dept
     * @return
     */
    int updateDept(Dept dept);

    /**
     * 删除部门
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /*** 查询所有部门 * @return */
    List<Dept> findDeptList();


}
