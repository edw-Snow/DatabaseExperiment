package com.myproject.web.filter;


import com.myproject.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录验证的过滤器
 * 每次登录成功存储随机cookie和session，每次登录界面加载成功遍历session，实现记住密码
 */

//@javax.servlet.annotation.WebFilter(value = {"/loginServlet"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}
