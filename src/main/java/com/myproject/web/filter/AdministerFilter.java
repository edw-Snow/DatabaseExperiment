package com.myproject.web.filter;


import com.myproject.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.annotation.WebFilter;

@WebFilter(value = {"/administer/*", "/administerStudent.html", "/administerTeacher.html"})
public class AdministerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        User administer = (User) session.getAttribute("administer");
        if (administer != null) {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}
