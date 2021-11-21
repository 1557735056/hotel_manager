package com.poppy.service.impl;

import com.poppy.dao.RoomTypeMapper;
import com.poppy.entity.RoomType;
import com.poppy.entity.vo.RoomTypeVo;
import com.poppy.service.RoomTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author poppy
 * @Date 2021/11/5 17:54
 * @Version 1.0
 */
@Service
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService {
    @Resource
    private RoomTypeMapper roomTypeMapper;
    /**
     * 获取所有房型
     *
     * @param roomTypeVo
     * @return
     */
    @Override
    public List<RoomType> getAllRoomType(RoomTypeVo roomTypeVo) {
        return roomTypeMapper.getAllRoomType(roomTypeVo);
    }

    /**
     * 添加房型
     *
     * @param roomType
     * @return
     */
    @Override
    public int addRoomType(RoomType roomType) {
        //可用房间数默认是全部的房间数
        roomType.setAvilableNum(roomType.getRoomNum());
        //已经入住的房间数量
        roomType.setLivedNum(0);
        return roomTypeMapper.addRoomType(roomType);
    }

    /**
     * 修改房型
     *
     * @param roomType
     * @return
     */
    @Override
    public int updateRoomType(RoomType roomType) {
        return roomTypeMapper.updateRoomType(roomType);
    }

    /**
     * 添加房型
     *
     * @param roomType
     * @return
     */
    @Override
    public int deleteRoomTypeById(Integer  id) {
        return roomTypeMapper.deleteRoomTypeById(id);
    }
}
