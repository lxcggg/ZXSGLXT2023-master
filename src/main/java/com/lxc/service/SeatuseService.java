package com.lxc.service;

import com.lxc.entity.Seatuse;

import java.util.List;

/**
 * @Description: TODO
 * @Author: SuMin
 * @Date: 2023/8/25
 **/
public interface SeatuseService {

    public List<Seatuse> findSeatuseByid(Integer userid);
}
