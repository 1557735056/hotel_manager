package com.poppy.service.impl;

import com.poppy.dao.RoomMapper;
import com.poppy.entity.Room;
import com.poppy.entity.vo.RoomVo;
import com.poppy.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author poppy
 * @Date 2021/11/7 14:56
 * @Version 1.0
 */

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Resource
    private RoomMapper roomMapper;
    /**
     * 查询房间列表
     *
     * @param roomVo
     * @return
     */
    @Override
    public List<Room> findRoomListByPage(RoomVo roomVo) {
        return roomMapper.findRoomListByPage(roomVo);
    }

    /**
     * 添加房间列表
     *
     * @param room
     * @return
     */
    @Override
    public int addRoom(Room room) {
        return roomMapper.addRoom(room);
    }

    /**
     * 更新房间信息
     *
     * @param room
     * @return
     */
    @Override
    public int updateRoom(Room room) {
        return roomMapper.updateRoom(room);
    }

    /**
     * 删除房间信息
     *
     * @param id
     * @return
     */
    @Override
    public int deleteRoom(Integer id) {
        return roomMapper.deleteRoom(id);
    }

    /**
     * 根据id查询房间号
     *
     * @param id
     * @return
     */
    @Override
    public Room findById(Integer id) {
        return roomMapper.findById(id);
    }

    @Override
    public List<Room> findRoomByFloorId() {

        return  roomMapper.findRoomByFloorId();
    }
}
