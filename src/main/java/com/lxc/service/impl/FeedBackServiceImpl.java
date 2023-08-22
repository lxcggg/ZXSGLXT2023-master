package com.lxc.service.impl;

import com.lxc.dao.FeedBackMapper;
import com.lxc.entity.FeedBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackServiceImpl {
    @Autowired
    FeedBackMapper feedBackMapper;

    //插入数据，反馈结果
    //插入投诉ID和反馈内容
    public boolean insertCpText(FeedBack feedBack){
        int i = feedBackMapper.insertSelective(feedBack);
        return i > 0;
    }

    /*获取全部反馈信息*/
    public List<FeedBack> getAll(){
        List<FeedBack> feedBacks = feedBackMapper.selectByExample(null);
        return feedBacks;
    }
}
