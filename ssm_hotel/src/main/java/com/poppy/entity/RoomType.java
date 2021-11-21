package com.poppy.entity;

/**
 * @Author poppy
 * @Date 2021/11/5 14:46
 * @Version 1.0
 */
public class RoomType {
    private Integer id;
    private String typename;
    private String photo;
    private Double price;
    private Integer liveNum;
    private Integer bedNum;
    private Integer roomNum;
    private Integer reservedNum;
    private Integer avilableNum;
    private Integer livedNum;
    private Integer status;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getLiveNum() {
        return liveNum;
    }

    public void setLiveNum(Integer liveNum) {
        this.liveNum = liveNum;
    }

    public Integer getBedNum() {
        return bedNum;
    }

    public void setBedNum(Integer bedNum) {
        this.bedNum = bedNum;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public Integer getReservedNum() {
        return reservedNum;
    }

    public void setReservedNum(Integer reservedNum) {
        this.reservedNum = reservedNum;
    }

    public Integer getAvilableNum() {
        return avilableNum;
    }

    public void setAvilableNum(Integer avilableNum) {
        this.avilableNum = avilableNum;
    }

    public Integer getLivedNum() {
        return livedNum;
    }

    public void setLivedNum(Integer livedNum) {
        this.livedNum = livedNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
