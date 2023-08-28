package com.lxc.dao;

import com.lxc.entity.Complain;
import com.lxc.entity.ComplainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface ComplainMapper {
    long countByExample(ComplainExample example);

    int deleteByExample(ComplainExample example);

    int deleteByPrimaryKey(Integer cpid);

    int insert(Complain record);

    int insertSelective(Complain record);

    List<Complain> selectByExample(ComplainExample example);

    Complain selectByPrimaryKey(Integer cpid);

    int updateByExampleSelective(@Param("record") Complain record, @Param("example") ComplainExample example);

    int updateByExample(@Param("record") Complain record, @Param("example") ComplainExample example);

    int updateByPrimaryKeySelective(Complain record);

    int updateByPrimaryKey(Complain record);

    List<Complain> selectByuserId(Integer userId);

    int insertSelect(Complain complain);

}