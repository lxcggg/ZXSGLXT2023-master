package com.lxc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxc.entity.Complain;
import com.lxc.entity.FeedBack;
import com.lxc.entity.User;
import com.lxc.service.impl.FeedBackServiceImpl;
import com.lxc.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class FeedBackController {



    @Autowired
    FeedBackServiceImpl feedBackService;

    //到投诉反馈页面并且获取数据库中feedback数据
    @GetMapping("/toFeedback")
    public String toFeedbackPage(Map<String ,Object> map){
        List<FeedBack> feedBacks = feedBackService.getAll();
        map.put("fds",feedBacks);
        return "Feedback";
    }
    //插入数据方法
    @PostMapping("/insertCpText")
    @ResponseBody
    public Msg insertCpText(FeedBack feedBack){
        feedBack.setFdid(null);
        Date date = new Date();
        feedBack.setFdtime(date);
        boolean b = feedBackService.insertCpText(feedBack);
        if(b==true){
            return Msg.success().add("va_msg","反馈内容插入成功！");
        }else {
            return Msg.fail().add("va_msg","反馈内容插入失败！");
        }
    }


    @RequestMapping("/toFeedback2")
    public String touserFeedbackPage(Map<String,Object> map, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); // 假设用户对象存在于名为"user"的session属性中
        if (user == null) {
            // 当前用户未登录或session中没有"user"属性
            System.out.println("session中没有\"user\"属性" ); // 重定向到登录页面或其他处理方式
        }

        Integer userId = user.getUserid();
        if (userId == null) {
            // 当前用户对象中没有userid字段
            System.out.println("当前用户对象中没有userid字段" );// 重定向到登录页面或其他处理方式
        }

        System.out.println("当前用户的userid为：" + userId); // 输出userid到控制台

        List<FeedBack> Feedback2 = feedBackService.getFeedbackById(userId);
        map.put("fds",Feedback2);
        return "Feedback2";
    }





}
