package com.lxc.dao;

import com.lxc.entity.Statistic;
import com.lxc.entity.StatisticExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface StatisticMapper {
    long countByExample(StatisticExample example);

    int deleteByExample(StatisticExample example);

    int deleteByPrimaryKey(Integer staid);

    int insert(Statistic record);

    int insertSelective(Statistic record);

    List<Statistic> selectByExample(StatisticExample example);

    Statistic selectByPrimaryKey(Integer staid);

    int updateByExampleSelective(@Param("record") Statistic record, @Param("example") StatisticExample example);

    int updateByExample(@Param("record") Statistic record, @Param("example") StatisticExample example);

    int updateByPrimaryKeySelective(Statistic record);

    int updateByPrimaryKey(Statistic record);
}