package com.lxc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxc.entity.Manager;
import com.lxc.entity.Statistic;
import com.lxc.entity.User;
import com.lxc.service.impl.UserServiceImpl;
import com.lxc.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    /*
    * queryUserlike  通过用户姓名进行模糊查询
    * */
    @GetMapping("/queryUserlike/{username}")
    @ResponseBody
    public Msg queryUserlikeId(@RequestParam(value = "pn",defaultValue = "1") Integer pn,
                               @PathVariable("username") String username){
        PageHelper.startPage(pn,20);
        List<User> users = userService.queryByUserName(username);
        PageInfo<User> page=new PageInfo<User>(users,5);
        return Msg.success().add("pageInfo",page);
    }

    /*
    * 前端发送请求后端分页数据，
    * */
    @GetMapping("/userTables")
    @ResponseBody
    public Msg userTables(@RequestParam(value = "pn",defaultValue = "1") Integer pn){
        PageHelper.startPage(pn,7);
        List<User> users = userService.getAll();
        PageInfo<User> page=new PageInfo<User>(users,5);
        return Msg.success().add("pageInfo",page);
    }

    //到tables页面
    @RequestMapping("/usertables")
    public String toUserTables(Map<String,Object> map){
        List<User> users = userService.getAll();
        map.put("users",users);
        return "Tables";
    }

    //点击编辑按键传入userid查询用户，返回用户信息
    @GetMapping("/queryUserByID/{userid}")
    @ResponseBody
    public Msg queryUserID(@PathVariable("userid") Integer userid,Map<String,Object> map){
        User users = userService.getUserByID(userid);
        return Msg.success().add("users",users);
    }
    /*
    * 查询全部用户
    */
    /*@GetMapping("/user22")
    public String getAllUser(Map<String,Object> map){
        List<User> users = userService.getAll();
        map.put("users",users);
        return "Dashboard";
    }*/



    /*
     * 用户删除请求
     */
    @DeleteMapping(value = "/user/{ids}")
    @ResponseBody
    public Msg delEmp(@PathVariable("ids") String ids) {
        if(ids.contains("-")) {
            List<Integer> delids = new ArrayList<Integer>();
            String[] split = ids.split("-");
            for (String string : split) {
                delids.add(Integer.parseInt(string));
            }
            userService.batchUser(delids);
        }else {
            int id = Integer.parseInt(ids);
            userService.deleteUser(id);
        }
        return Msg.success();
    }

    /*
     * 用户更新
     */
    @PutMapping(value = "/user/{userID}")
    @ResponseBody
    public Msg saveEmp(User user) {
        System.out.println(user);
        userService.updateUser(user);
        return Msg.success();
    }

    /*
     * 根据用户ID查询结果
     */
    @GetMapping(value = "user/{id}")
    @ResponseBody
    public Msg getEmp(@PathVariable(value = "id")Integer userID) {
        User user = userService.getUserByID(userID);
        return Msg.success().add("user", user);
    }
    /*
     * 检查用户名是否可用
     */
    @RequestMapping(value = "/checkAddUser")
    @ResponseBody
    public Msg checkAddUser(@RequestParam("username")String userName) {
        //先判断用户名是否合法的表达式
        String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]+$)";
        boolean c = userName.matches(regex);
        if(!c) {
            return Msg.fail().add("va_msg", "用户名可以是2-5位中文，或者6-16位英文和数字的组合");
        }
        //数据库用户名重复校验
        boolean b = userService.checkUserName(userName);
        if(b) {
            return Msg.success().add("va_msg", "用户名可用");
        }else {
            return Msg.fail().add("va_msg",	"用户名已存在");
        }
    }
    /*校验手机号码格式*/
    @RequestMapping("/checkAddTel")
    @ResponseBody
    public Msg checkAddTel(@RequestParam(value = "usertel")String userTel1) {
        String userTel = userTel1.trim();
        String regex = "(^1\\d{10}$)";
        boolean matches = userTel.matches(regex);
        if(matches == true){
            return Msg.success().add("va_msg","");
        }else{
            return Msg.fail().add("va_msg","手机号必须以1开头，11位的数字！");
        }
    }
    /*校验年龄码格式*/
    @RequestMapping("/checkAddAge")
    @ResponseBody
    public Msg checkAddAge(@RequestParam(value = "userage")Integer userAge1) {
        String s = userAge1.toString();
        String userTel = s.trim();
        String regex = "(^\\d{1,3}$)";
        boolean matches = userTel.matches(regex);
        if(matches == true){
            return Msg.success().add("va_msg","");
        }else{
            return Msg.fail().add("va_msg","请输入数字");
        }
    }
    /*校验密码格式*/
    @RequestMapping("/checkAddPwd")
    @ResponseBody
    public Msg checkAddPwd(@RequestParam(value = "userpwd")String userPwd1) {
        String userPwd = userPwd1.trim();
        String regex = "(^[a-zA-Z0-9_-]{6,16}$)";
        boolean matches = userPwd.matches(regex);
        if(matches == true){
            return Msg.success().add("va_msg","");
        }else{
            return Msg.fail().add("va_msg","密码格式不正确：必须超过6位，小于16位的英文和数字！");
        }
    }
    /****************************************************插入*********
     * 点击保存按钮，新增用户
     * */
    @PostMapping("/insertUser")
    @ResponseBody
    public Msg insertUser(User user){
        user.setUserid(null);
        userService.insertUser(user);
        return Msg.success().add("va_msg","插入成功！");
    }

    //点击更新按钮，更新修改的数据
    @PutMapping("/updateUser")
    @ResponseBody
    public Msg updateUser(User user){
        userService.updateUser(user);
        return Msg.success().add("va_msg","插入成功！");
    }

    //用户删除
    @DeleteMapping("/deleteUser/{userid}")
    @ResponseBody
    public Msg deleteEmployee(@PathVariable("userid") Integer userid){
        boolean b = userService.deleteUser(userid);
        if (b){
            return Msg.success().add("va_msg","用户删除成功!");
        }else {
            return Msg.fail().add("va_msg","用户删除失败！");
        }
    }

    /*
    * 前端ajax请求，查询userid是否在表中存在
    * */
    @GetMapping("/checkUserID")
    @ResponseBody
    public Msg checkUserID(@RequestParam("userid") Integer userid){
        boolean b = userService.checkUserID(userid);
        if(b){
            return Msg.success().add("va_msg","用户名存在!");
        }else {
            return Msg.fail().add("va_msg","用户名不存在，重新输入！");
        }
    }

    //登陆和忘记密码==》检查用户名是否存在
    @GetMapping("/checkLoginUserName")
    @ResponseBody
    public Msg checkLoginUserName(@RequestParam("username") String username2){
        String username = username2.trim();
        String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]+$)";

        boolean c = username.matches(regex);
        if(!c) {
            return Msg.fail().add("va_msg", "用户名是2-5位中文，或者6-16位英文和数字的组合");
        }
        boolean b = userService.checkUserName(username);
        if (b==true){
            return Msg.fail().add("va_msg","用户名不存在！");
        }else {
            return Msg.success().add("va_msg","");
        }
    }
    //登陆检查密码
    @GetMapping("/checkLoginUserPwd")
    @ResponseBody
    public Msg checkLoginUserPwd(@RequestParam("userpwd") String userpwd1){
        String userpwd = userpwd1.trim();
        String regex = "(^[a-zA-Z0-9_-]{6,16}$)";
        boolean matches = userpwd.matches(regex);
        if(matches == true){
            return Msg.success().add("va_msg"," ");
        }else{
            return Msg.fail().add("va_msg","密码格式不正确：必须超过6位，小于16位！");
        }
    }

    @PostMapping("/loginD2")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("userpwd") String userpwd,
                            Map<String,Object> map,Model model, HttpSession session) {
        User user = userService.userLogin(username,userpwd);
        if (user == null) {
            map.put("msg","账号密码错误，请重新输入账号和密码！");
            return "Login2";
        } else {

            session.setAttribute("user",user);

            return "redirect:/user23";
        }


    }

    //点击进入按钮校验用户名和密码是否正确
    /*@PostMapping("/loginD2")
    public String loginDashboard(@RequestParam("username") String username2,
                                 @RequestParam("userpwd") String userpwd1,
                                 Map<String,Object> map, HttpSession session){
        *//*
         * ==true没有该用户
         * ==false 用户存在
         * *//*
        String username = username2.trim();
        String userpwd = userpwd1.trim();
        if(username != "" && userpwd1!=""){
            User user = userService.selectByUserName(username);
            boolean name = userService.checkUserName(username);
            boolean pwd = userService.checkUserPwd(userpwd);
            if(name == false && pwd ==false ){
//                System.out.println("用户名或密码正确！");

                session.setAttribute("user",user);

                return "redirect:/user23";
            }else{
//                System.out.println("密码错误！");
                map.put("msg","账号密码错误，请重新输入账号和密码！");
                return "Login2";
            }
        }else {
            map.put("msg","请输入账号or密码！");
            return "Login2";
        }
    }*/
    @GetMapping("/user23")
    public String getUser(Map<String,Object> map){
        List<User> users = userService.getAll();
        map.put("users",users);
        return "userList";
    }
    //进入注册页面
    @RequestMapping("/register02")
    public String toRegisterPage(){
        return "Register02";
    }

    @PostMapping("/registerUser")
    public String registerUser(User user, Map<String,Object> map){
        user.setUserid(null);
        boolean flag = userService.createUser(user);
        if(flag == true){
            map.put("msg","插入成功！");
            return "redirect:main.html";
        }else{
            map.put("msg","插入失败！");
            return "Register02";
        }
    }
//用户退出登录
    @RequestMapping("/logout2")
    public String toLoginPage(HttpSession session){
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            session.removeAttribute(attributeNames.nextElement());
        }
        return "Login2";
    }



   /* @PostMapping("/modifyEmployee")
    public String modifyEmployee(Employee employee, RedirectAttributes redirectAttrs) {
        System.out.println(employee+"====================");
        if (StringUtils.hasText(employee.getStatus())) {
            if (employee.getStatus().equals("启用")) {
                employee.setStatus("1");
            } else {
                employee.setStatus("0");
            }
        }
        if (StringUtils.hasText(employee.getSex())) {
            if (employee.getSex().equals("男")){
                employee.setSex("1");
            }else {
                employee.setSex("0");
            }
        }
        Integer flag = employeeService.updateEmployee(employee);
        redirectAttrs.addAttribute("errorMessage", "修改成功！");
        return "redirect:/employee";
    }
    */


    @PutMapping("/resetpwd2ForUser")
    public String backLogin2(User user, HttpSession session, Map<String,Object> map) {
        Integer flag = userService.updateUserById(user);
        session.setAttribute("msg","修改成功！");
        session.setAttribute("user",user);
        return "redirect:/user23";
    }

    @GetMapping("/clearSessionMsg")
    public String clearSessionMsg(HttpSession session){

        session.removeAttribute("msg");

        return "redirect:/user23";
    }



    @RequestMapping("/toUserCount")
    public String toUserCount(Map<String,Object> map){
        List<User> users = userService.getAll();
        map.put("users",users);
        return "UserCount";
    }

    @RequestMapping("/index")
    public String LoginPage(HttpSession session){
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            session.removeAttribute(attributeNames.nextElement());
        }
        return "Login";
    }

}
