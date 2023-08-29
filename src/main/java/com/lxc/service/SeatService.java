package com.lxc.service;

import com.lxc.entity.SeatWithUserIdDTO;

import java.util.List;

public interface SeatService {
    List<SeatWithUserIdDTO> selectUserIdSeatIDAndRoomId(SeatWithUserIdDTO seatWithUserIdDTO);
}
