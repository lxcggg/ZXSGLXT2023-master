package com.lxc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxc.entity.Seatuse;
import com.lxc.entity.User;
import com.lxc.service.impl.SeatuseServiceImpl;
import com.lxc.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
/*
*
* 座位使用统计
*
* */
@Controller
public class SeatuseController {
    @Autowired
    SeatuseServiceImpl seatuseService;

    /*
     *  通过用户ID进行查询
     * */
    @GetMapping("/queryByUserID/{userid}")
    @ResponseBody
    public Msg queryUserID(@RequestParam(value = "pn",defaultValue = "1") Integer pn,
                           @PathVariable("userid") Integer userid){
        PageHelper.startPage(pn,20);
        List<Seatuse> users = seatuseService.queryByUserID(userid);
        PageInfo<Seatuse> page=new PageInfo<Seatuse>(users,5);
        return Msg.success().add("pageInfo",page);
    }

    @GetMapping("/seatUseID")
    public String toSeatUserPage2(Map<String,Object> map,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Seatuse> seatusers = seatuseService.findSeatuseByid(user.getUserid());
        map.put("sus",seatusers);
        return "SeatUse2";
    }
    /*
     * 用户到座位使用统计页面
     * */
    /*@RequestMapping("/seatUseID")
    public String toSeatUserPage2(Map<String,Object> map, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");// 假设用户对象存在于名为"user"的session属性中
        List<Seatuse> seatusers = seatuseService.queryByUserID(user.getUserid());

        return "SeatUse2";
    }*/

    /*
    * 到座位使用统计页面
    * */
    @RequestMapping("/seatUseCount")
    public String toSeatUserPage(Map<String,Object> map){
        List<Seatuse> seatusers = seatuseService.getAll();
        map.put("sus",seatusers);
        return "SeatUse";
    }


    @GetMapping("/seatUsePage")
    @ResponseBody
    public Msg SeatUserInfo(@RequestParam(value = "pn",defaultValue = "1") Integer pn,
                            HttpServletRequest request){
        PageHelper.startPage(pn,6);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Seatuse> seatuseByid = seatuseService.findSeatuseByid(user.getUserid());
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就好了
        //封装了详细的分页信息，包括有我们查询出来的数据，传入连续显示的页数
        PageInfo<Seatuse> page=new PageInfo<Seatuse>(seatuseByid,5);
        return Msg.success().add("pageInfo",page);
    }



    /*
    * 插入数据到记录表中
    * */
    @PostMapping("/insertSUInfo")
    @ResponseBody
    public Msg insertSUInfo(Seatuse seatuse, @RequestParam("status") String status){
        boolean b = seatuseService.insertSeatUseInfo(seatuse, status);
        if (b){
            return Msg.success().add("va_msg","插入到座位使用记录表成功！");
        }else {
            return Msg.fail().add("va_msg","插入到座位使用记录表失败！");
        }
    }




    @DeleteMapping("/deleteSeatUseByID/{seatuserid}")
    @ResponseBody
    public Msg deleteSeatUserInfo(@PathVariable("seatuserid") Integer seatuserid){
        boolean b = seatuseService.deleteSeatUserInfo(seatuserid);
        if(b){
            return Msg.success().add("va_msg","删除成功座位使用记录");
        }else {
            return Msg.fail().add("va_msg","删除失败座位使用记录");
        }
    }

    /*
    * 获取状态为1的数据条数
    * */
    @GetMapping("/state1Count/{state}")
    @ResponseBody
    public Msg state1Count(@PathVariable("state") String state){
        long l = seatuseService.queryStateCount(state);
        return Msg.success().add("state1Count",l);
    }
}
