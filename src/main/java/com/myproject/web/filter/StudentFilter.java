package com.myproject.web.filter;


import com.myproject.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@javax.servlet.annotation.WebFilter(value = {"/student.html","/student/*"})
public class StudentFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        User student = (User) session.getAttribute("student");
        if (student != null) {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}
