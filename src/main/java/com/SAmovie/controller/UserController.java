package com.SAmovie.controller;

import com.SAmovie.model.MovieList;
import com.SAmovie.model.Typelist;
import com.SAmovie.model.User;
import com.SAmovie.model.UserInfo;
import com.SAmovie.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by scamonia on 2018/4/21.
 */
@Controller
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/login")
    public String logpage(){
        System.out.println("已进入logpage");
        return "user/login";
    }

    @ResponseBody
    @RequestMapping("/login/judgeaccount")
    public boolean login(HttpServletRequest httpServletRequest,@RequestParam("loginname") String username, @RequestParam("password") String pwd){
        System.out.println("已进入login"+username+pwd);
        User queryuser = userService.queryuserByname(username);

        if(queryuser == null || !queryuser.getPwd().equals(pwd)){
            return false;
        }else {
            System.out.println("查到的为"+queryuser.getId()+queryuser.getUsername()+queryuser.getPwd());
            String uid = queryuser.getId();
            String uname = queryuser.getUsername();
            httpServletRequest.getSession().removeAttribute("uid");
            httpServletRequest.getSession().removeAttribute("uname");
            httpServletRequest.getSession().setAttribute("uid",uid);
            httpServletRequest.getSession().setAttribute("uname",uname);
            return true;
        }
    }

    @RequestMapping("/register")
    public String regpage(){
        System.out.println("已进入regpage");
        return "user/register";
    }

    @ResponseBody
    @RequestMapping(value = "/register/judgeuserName",produces = "application/text; charset=utf-8")
    public String judgeuserName(@RequestParam("userName") String username){
        System.out.println("用户姓名检测"+username);
        int result = userService.judgeusername(username);
        String checkinfo;
        if (result ==  0){
            checkinfo = "该用户名可用，真是个好名字呢";
        }else {
            checkinfo = "可恶啊，这个称号被人抢先一步注册了";
        }
        return checkinfo;
    }

    @ResponseBody
    @RequestMapping(value = "/register/submit")
    public String sumbit(HttpServletRequest httpServletRequest,@RequestParam("userName") String username,@RequestParam("userPassword") String pwd,
     @RequestParam(value = "likelist",required = false) String[] selectlist){

        System.out.println("已进入submit"+username+pwd);


        String userid = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(userid);
        User user = new User(userid,username,pwd,selectlist);
        Typelist typelist = new Typelist();
        if (selectlist != null) {
            typelist.resetvalue(selectlist);
        }
        typelist.setId(userid);
        MovieList likedList = new MovieList(userid,"我的喜欢","dfl","defaultlike",userid);
        UserInfo userInfo = new UserInfo(userid,"未知","未知","没什么好说的");

        int result = userService.adduser(user);
        int typelistresult = userService.addtypelist(typelist);
        int mlistresult = userService.addmovielist(likedList);
        int userinfo = userService.adduserinfo(userInfo);

        System.out.println("返回结果为"+result);
        System.out.println("第二结果为"+typelistresult);
        System.out.println("第三结果为"+mlistresult);
        httpServletRequest.getSession().removeAttribute("uid");
        httpServletRequest.getSession().removeAttribute("uname");
        httpServletRequest.getSession().setAttribute("uid",userid);
        httpServletRequest.getSession().setAttribute("uname",username);
        return "success";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest){
        System.out.println("已进入logout");
        httpServletRequest.getSession().removeAttribute("uid");
        httpServletRequest.getSession().removeAttribute("uname");
        return "index/index";
    }
}
