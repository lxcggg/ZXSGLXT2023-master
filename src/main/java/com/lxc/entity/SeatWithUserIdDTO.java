package com.lxc.entity;

public class SeatWithUserIdDTO {
    private Seat seat;
    private Integer userid;
    private Integer seatid;
    private String seatstate;
    private Integer roomid;
    // 构造方法、getter 和 setter 方法省略


    public SeatWithUserIdDTO() {
    }

    public SeatWithUserIdDTO(Seat seat, Integer userid, Integer seatid, String seatstate, Integer roomid) {
        this.seat = seat;
        this.userid = userid;
        this.seatid = seatid;
        this.seatstate = seatstate;
        this.roomid = roomid;
    }


    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSeatid() {
        return seatid;
    }

    public void setSeatid(Integer seatid) {
        this.seatid = seatid;
    }

    public String getSeatstate() {
        return seatstate;
    }

    public void setSeatstate(String seatstate) {
        this.seatstate = seatstate;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }


    @Override
    public String toString() {
        return "SeatWithUserIdDTO{" +
                "seat=" + seat +
                ", userid=" + userid +
                ", seatid=" + seatid +
                ", seatstate='" + seatstate + '\'' +
                ", roomid=" + roomid +
                '}';
    }
}
