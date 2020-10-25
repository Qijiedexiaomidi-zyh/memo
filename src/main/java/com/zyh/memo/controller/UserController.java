package com.zyh.memo.controller;

import com.zyh.memo.entity.User;
import com.zyh.memo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author ZYH
 * @Date 2020/10/24 11:05
 * @Blog https://qijiedexiaomidi-zyh.github.io
 * @Faith 干就完了，不多哔哔
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录请求
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(User user, HttpSession session){
        User loginUser = userService.login(user);
        if (loginUser!=null){
            session.setAttribute("loginUser",loginUser);
            return "index";
        }else {
            session.setAttribute("message","您好，用户名或密码错误");
            return "login";
        }
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        return "login";
    }
}
