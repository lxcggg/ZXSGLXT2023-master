package com.lxc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxc.entity.Complain;
import com.lxc.entity.User;
import com.lxc.service.impl.ComplainServiceImpl;
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
public class ComplainController {

    @Autowired
    ComplainServiceImpl complainService;

    //来到投诉页面
    @RequestMapping("/toComplain")
    public String toComplainPage(Map<String,Object> map){
        List<Complain> complains = complainService.getAll();
        map.put("cps",complains);
        return "Complain";
    }
    //AJAX通过请求返回投诉列表
    @GetMapping("/complainList")
    @ResponseBody
    public Msg ComplainData(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,6);
        List<Complain> complains = complainService.getAll();
        PageInfo<Complain> page=new PageInfo<Complain>(complains,5);
        return Msg.success().add("pageInfo",page);
    }

    /*更新ps中的处理状态*/
    @PutMapping("/updatePs")
    @ResponseBody
    public Msg updatePss(Integer cpid){
        boolean b = complainService.updatePs(cpid);
        if (b == true){
            return Msg.success().add("va_msg","ps状态更新成功");
        }else{
            return Msg.fail().add("va_msg","ps状态更新失败");
        }
    }

    /*根据cpid删除投诉数据*/
    @DeleteMapping("/deleteCp/{cpid}")
    @ResponseBody
    public Msg deleteCp2(@PathVariable("cpid") int cpid){
        boolean b = complainService.deleteCp(cpid);
        if (b == true){
            return Msg.success().add("va_msg","cp删除成功");
        }else{
            return Msg.fail().add("va_msg","cp删除成功");
        }
    }

    //点击编辑按键传入userid查询用户，返回用户信息
    @GetMapping("/queryCpByID/{cpid}")
    @ResponseBody
    public Msg queryUserID(@PathVariable("cpid") Integer cpid){
        Complain cpByID = complainService.getCpByID(cpid);
        return Msg.success().add("cp",cpByID);
    }


    //来到用户投诉页面
    @RequestMapping("/userComplain")
    public String touserComplainPage(Map<String,Object> map, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); // 假设用户对象存在于名为"user"的session属性中
        if (user == null) {
            // 当前用户未登录或session中没有"user"属性
            System.out.println("ession中没有\"user\"属性" ); // 重定向到登录页面或其他处理方式
        }

        Integer userId = user.getUserid();
        if (userId == null) {
            // 当前用户对象中没有userid字段
            System.out.println("当前用户对象中没有userid字段" );// 重定向到登录页面或其他处理方式
        }

        System.out.println("当前用户的userid为：" + userId); // 输出userid到控制台

        List<Complain> complain1 = complainService.getComplainbyid(userId);
        map.put("cps",complain1);
        return "Complain1";
    }


    //AJAX通过请求返回投诉列表
    @GetMapping("/usercomplainList")
    @ResponseBody
    public Msg ComplainData(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Map<String,Object> map, HttpServletRequest request){
        PageHelper.startPage(pn,6);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); // 假设用户对象存在于名为"user"的session属性中
        if (user == null) {
            // 当前用户未登录或session中没有"user"属性
            System.out.println("ession中没有\"user\"属性" ); // 重定向到登录页面或其他处理方式
        }

        Integer userId = user.getUserid();
        if (userId == null) {
            // 当前用户对象中没有userid字段
            System.out.println("当前用户对象中没有userid字段" );// 重定向到登录页面或其他处理方式
        }

        System.out.println("当前用户的userid为：" + userId); // 输出userid到控制台

        List<Complain> complain1 = complainService.getComplainbyid(userId);
        PageInfo<Complain> page=new PageInfo<Complain>(complain1,5);
        return Msg.success().add("pageInfo",page);
    }

    //插入数据方法
    @PostMapping("/insertCp")
    @ResponseBody
    public Msg insertCp(Complain complain, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user != null ? user.getUserid() : null;

        if (userId != null) {
            complain.setUserid(userId);
            complain.setPs("未解决");
            Date date = new Date();
            complain.setCpdate(date);
            System.out.println("当前用户的userid为：" + userId);
            boolean success = complainService.insertCp(complain);
            if (success) {
                return Msg.success().add("va_msg", "反馈内容插入成功！");
            } else {
                return Msg.fail().add("va_msg", "反馈内容插入失败！");
            }
        } else {
            return Msg.fail().add("va_msg", "用户未登录！");
        }
    }



}
