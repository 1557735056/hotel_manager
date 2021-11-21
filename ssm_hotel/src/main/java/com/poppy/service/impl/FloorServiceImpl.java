package com.poppy.service.impl;

import com.poppy.dao.FloorMapper;
import com.poppy.entity.Floor;
import com.poppy.entity.vo.FloorVo;
import com.poppy.service.FloorService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author poppy
 * @Date 2021/11/5 13:50
 * @Version 1.0
 */
@Service
@Transactional
public class FloorServiceImpl implements FloorService {

    @Resource
    private FloorMapper floorMapper;
    /**
     * 查询楼层列表
     *
     * @param floorVo
     * @return
     */
    @Override
    public List<Floor> findFlowList(FloorVo floorVo) {
        return floorMapper.findFlowList(floorVo);
    }

    /**
     * 添加楼层
     *
     * @param floor
     * @return
     */
    @Override
    public int addFloor(Floor floor) {
        return floorMapper.addFloor(floor);
    }

    /**
     * 更新楼层
     *
     * @param floor
     * @return
     */
    @Override
    public int updateFloor(Floor floor) {
        return floorMapper.updateFloor(floor);
    }

    /**
     * 根据id删除楼层信息
     *
     * @param integer
     * @return
     */
    @Override
    public int deleteById(Integer integer) {
        return floorMapper.deleteById(integer);
    }
}
