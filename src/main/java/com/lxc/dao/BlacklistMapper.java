package com.lxc.dao;

import com.lxc.entity.Blacklist;
import com.lxc.entity.BlacklistExample;
import java.util.List;

import com.lxc.entity.Complain;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface BlacklistMapper {
    long countByExample(BlacklistExample example);

    int deleteByExample(BlacklistExample example);

    int deleteByPrimaryKey(Integer blackid);

    int insert(Blacklist record);

    int insertSelective(Blacklist record);

    List<Blacklist> selectByExample(BlacklistExample example);

    Blacklist selectByPrimaryKey(Integer blackid);

    int updateByExampleSelective(@Param("record") Blacklist record, @Param("example") BlacklistExample example);

    int updateByExample(@Param("record") Blacklist record, @Param("example") BlacklistExample example);

    int updateByPrimaryKeySelective(Blacklist record);

    int updateByPrimaryKey(Blacklist record);

    int  insertBlack(Blacklist blacklist);

}