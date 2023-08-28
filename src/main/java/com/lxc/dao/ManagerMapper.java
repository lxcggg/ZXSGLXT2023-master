package com.lxc.dao;

import com.lxc.entity.Manager;
import com.lxc.entity.ManagerExample;
import java.util.List;

import com.lxc.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface ManagerMapper {

    @Select("select * from manager where managername=#{managername} and managerpwd=#{managerpwd}")
    Manager manageLogin(@Param("managername") String managerName, @Param("managerpwd") String password);

    long countByExample(ManagerExample example);

    int deleteByExample(ManagerExample example);

    int deleteByPrimaryKey(Integer managerid);

    int insert(Manager record);

    int insertSelective(Manager record);

    List<Manager> selectByExample(ManagerExample example);

    Manager selectByPrimaryKey(Integer managerid);

    int updateByExampleSelective(@Param("record") Manager record, @Param("example") ManagerExample example);

    int updateByExample(@Param("record") Manager record, @Param("example") ManagerExample example);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
}
