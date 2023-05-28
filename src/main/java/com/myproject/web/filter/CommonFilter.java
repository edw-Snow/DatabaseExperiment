package com.myproject.web.filter;


import com.myproject.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@javax.servlet.annotation.WebFilter(value = {"/administerCourse.html","/common/*"})
public class CommonFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        User student = (User) session.getAttribute("student");
        User administer = (User) session.getAttribute("administer");
        if (student != null || administer!=null) {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}
