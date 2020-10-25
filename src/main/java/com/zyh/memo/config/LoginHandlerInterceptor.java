package com.zyh.memo.config;
import com.zyh.memo.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser != null){
            return true;
        }else {
            request.setAttribute("message","没有权限，请先登录");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }
    }
}
