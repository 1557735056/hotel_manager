package com.poppy.service;

import com.poppy.entity.RoomType;
import com.poppy.entity.vo.RoomTypeVo;

import java.util.List;

/**
 * @Author poppy
 * @Date 2021/11/5 17:54
 * @Version 1.0
 */
public interface RoomTypeService {
    /**
     * 获取所有房型
     * @param roomTypeVo
     * @return
     */
    List<RoomType> getAllRoomType(RoomTypeVo roomTypeVo);

    /**
     * 添加房型
     * @param roomType
     * @return
     */
    int addRoomType(RoomType roomType);
    /**
     * 修改房型
     * @param roomType
     * @return
     */
    int updateRoomType(RoomType roomType);

    /**
     * 添加房型
     * @param id
     * @return
     */
    int deleteRoomTypeById(Integer id);
}
