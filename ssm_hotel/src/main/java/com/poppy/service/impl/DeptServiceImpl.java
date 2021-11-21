package com.poppy.service.impl;

import com.poppy.dao.DeptMapper;
import com.poppy.entity.vo.DeptVo;
import com.poppy.entity.Dept;
import com.poppy.service.DeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author poppy
 * @Date 2021/11/3 10:00
 * @Version 1.0
 */
@Service
@Transactional
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;


    @Override
    public List<Dept> findDeptListByPage(DeptVo deptVo) {
        return deptMapper.findDeptListByPage(deptVo);
    }

    @Override
    public int addDept(Dept dept) {
        //保存创建时间
        dept.setCreateDate(new Date());
        return deptMapper.addDept(dept);
    }

    @Override
    public int updateDept(Dept dept) {
        return deptMapper.updateDept(dept);
    }

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) {
        return deptMapper.deleteById(id);
    }

    /*** 查询所有部门 * @return */
    @Override
    public List<Dept> findDeptList() {
        return deptMapper.findDeptList();
    }

}
