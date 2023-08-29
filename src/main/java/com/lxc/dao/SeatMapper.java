package com.lxc.dao;

import com.lxc.entity.Seat;
import com.lxc.entity.SeatExample;
import java.util.List;

import com.lxc.entity.SeatWithUserIdDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface SeatMapper {
    long countByExample(SeatExample example);

    int deleteByExample(SeatExample example);

    int deleteByPrimaryKey(Integer seatid);

    int insert(Seat record);

    int insertSelective(Seat record);

    @Select("SELECT s.*, sr.userID AS userid\n" +
            "FROM seat s\n" +
            "LEFT JOIN seat_resver sr ON s.seatID = sr.seatID;")
    List<SeatWithUserIdDTO> selectUserIdSeatIDAndRoomId(SeatWithUserIdDTO seatWithUserIdDTO);

    List<Seat> selectByExample(SeatExample example);

    Seat selectByPrimaryKey(Integer seatid);

    int updateByExampleSelective(@Param("record") Seat record, @Param("example") SeatExample example);

    int updateByExample(@Param("record") Seat record, @Param("example") SeatExample example);

    int updateByPrimaryKeySelective(Seat record);

    int updateByPrimaryKey(Seat record);
}
