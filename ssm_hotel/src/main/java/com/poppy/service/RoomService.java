package com.poppy.service;

import com.poppy.entity.Room;
import com.poppy.entity.vo.RoomVo;

import java.util.List;

/**
 * @Author poppy
 * @Date 2021/11/7 14:55
 * @Version 1.0
 */
public interface RoomService {

    /**
     *查询房间列表
     * @param roomVo
     * @return
     */
    List<Room> findRoomListByPage(RoomVo roomVo);

    /**
     * 添加房间列表
     * @param room
     * @return
     */
    int addRoom(Room room);

    /**
     * 更新房间信息
     * @param room
     * @return
     */
    int updateRoom(Room room);

    /**
     * 删除房间信息
     * @param id
     * @return
     */
    int deleteRoom(Integer id);

    /**
     * 根据id查询房间号
     * @param id
     * @return
     */
    Room findById(Integer id);


    /**
     * 根据楼层查询
     * @param size
     * @return
     */
    List<Room> findRoomByFloorId();
}
