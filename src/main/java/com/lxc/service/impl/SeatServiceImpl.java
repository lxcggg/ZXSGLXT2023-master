package com.lxc.service.impl;

import com.lxc.dao.SeatMapper;
import com.lxc.entity.Seat;
import com.lxc.entity.SeatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl {
    @Autowired
    SeatMapper seatMapper;

    /*
    * 获取座位信息
    * */
    public List<Seat> getAll(){
        List<Seat> seats = seatMapper.selectByExample(null);
        return seats;
    }

    /*
    * 修改预约状态为0 ，取消预约
    * */
    public boolean updateSeatState(Integer seatid){
        SeatExample example = new SeatExample();
        SeatExample.Criteria criteria =  example.createCriteria();
        criteria.andSeatidEqualTo(seatid);
        Seat seat = new Seat();
        seat.setSeatstate("0");
        int i = seatMapper.updateByExampleSelective(seat, example);
        return i > 0;
    }
    /*
     * 修改预约状态为1 ，已预约
     * */
    public boolean updateSeatState1(Integer seatid,String status){
        SeatExample example = new SeatExample();
        SeatExample.Criteria criteria =  example.createCriteria();
        criteria.andSeatidEqualTo(seatid);
        Seat seat = new Seat();
        if (status.equals("可预约")){
            seat.setSeatstate("1");
        }else if (status.equals("立即上座")){
            seat.setSeatstate("2");
        }else {
            seat.setSeatstate(null);
        }

        int i = seatMapper.updateByExampleSelective(seat, example);
        return i > 0;
    }
}
