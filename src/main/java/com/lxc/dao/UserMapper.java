package com.lxc.dao;

import com.lxc.entity.Manager;
import com.lxc.entity.User;
import com.lxc.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    @Select("select * from user where username=#{username} and userpwd=#{userpwd}")
    User userLogin(@Param("username") String username, @Param("userpwd") String userpwd);

    @Select("select * from user where username=#{username}")
    User selectByUserName(@Param("username") String username);

    @Update("update user set usertel=#{usertel},userpwd=#{userpwd} where userid=#{userid}")
    public Integer updateUserById(User user);


    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}