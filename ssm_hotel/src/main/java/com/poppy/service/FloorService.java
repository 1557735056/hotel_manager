package com.poppy.service;

import com.poppy.entity.Floor;
import com.poppy.entity.vo.FloorVo;

import java.util.List;

/**
 * @Author poppy
 * @Date 2021/11/5 13:49
 * @Version 1.0
 */
public interface FloorService {
    /**
     * 查询楼层列表
     * @param floorVo
     * @return
     */
    List<Floor> findFlowList(FloorVo floorVo);


    /**
     * 添加楼层
     * @param floor
     * @return
     */
    int addFloor(Floor floor);
    /**
     * 更新楼层
     * @param floor
     * @return
     */
    int updateFloor(Floor floor);
    /**
     * 根据id删除楼层信息
     * @param integer
     * @return
     */
    int deleteById(Integer integer);
}
