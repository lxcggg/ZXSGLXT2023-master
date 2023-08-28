package com.lxc.service.impl;

import com.lxc.dao.ComplainMapper;
import com.lxc.entity.Complain;
import com.lxc.entity.ComplainExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
*
*
* 投诉反馈
*
* */


@Service
public class ComplainServiceImpl {

    @Autowired
    ComplainMapper complainMapper;
    //查询投诉的数据
    public List<Complain> getAll(){
        List<Complain> complains = complainMapper.selectByExample(null);
        return complains;
    }

    /*更新ps中的处理状态*/
    public boolean updatePs(Integer cpid){
        ComplainExample example = new ComplainExample();
        ComplainExample.Criteria criteria = example.createCriteria();
        criteria.andCpidEqualTo(cpid);
        Complain complain = new Complain();
        complain.setPs("已处理");
        int i = complainMapper.updateByExampleSelective(complain, example);
        return i>0;
    }
    /*根据cpid删除整条数据*/
    public boolean deleteCp(int cpid){
        int i = complainMapper.deleteByPrimaryKey(cpid);
        return i > 0;
    }

    public Complain getCpByID(Integer cpid){
        Complain complain = complainMapper.selectByPrimaryKey(cpid);
        return complain;
    }



    public List<Complain> getComplainbyid(Integer userId){
        System.out.println("当前用户的userid为：" + userId);
        List<Complain> complain1 = complainMapper.selectByuserId(userId);
        return complain1;
    }
    //插入投诉ID和反馈内容
    public boolean insertCp(Complain complain){
        int i = complainMapper.insertSelect(complain);
        return i > 0;
    }


}
