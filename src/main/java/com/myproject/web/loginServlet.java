package com.myproject.web;

import com.alibaba.fastjson.JSON;
import com.myproject.mapper.DatabaseMapper;
import com.myproject.pojo.User;
import com.myproject.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@WebServlet("/loginServlet/*")
public class loginServlet extends BaseServlet {

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();
        DatabaseMapper mapper = sqlSession.getMapper(DatabaseMapper.class);
        BufferedReader br = req.getReader();
        String params = br.readLine();
        User login = JSON.parseObject(params, User.class);
        String username = login.getUsername();
        String password = login.getPassword();
        User user = mapper.loginAdministerTest(username, password);
        HttpSession session = req.getSession();
        if (user != null) {
            //跳转页面，并显示成功登录管理员
            resp.getWriter().write("administer");
            session.setAttribute("administer",user);
            sqlSession.close();
            return;
        }
        User user1 = mapper.loginStudentTest(username, password);
        if (user1 != null) {
            //跳转页面，并显示成功登录学生
            resp.getWriter().write("student");
            session.setAttribute("student", user1);
            sqlSession.close();
            return;
        }
        //回复错误信息
        resp.getWriter().write("notFound");
    }

    //学生登录时的身份显示
    public void getUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User student = (User) session.getAttribute("student");
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(student.getUsername());
    }

    //管理员cookie
    public void administerCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = "administer";
        name = URLEncoder.encode(name, "UTF-8");
        Cookie cookie = new Cookie("administer", name);
        resp.addCookie(cookie);
    }

    //学生cookie
    public void studentCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User student =(User) session.getAttribute("student");
        String name = student.getUsername();
        name = URLEncoder.encode(name, "UTF-8");
        Cookie cookie = new Cookie("student", name);
        resp.addCookie(cookie);
    }

    //获取cookie
    public void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        PrintWriter writer = resp.getWriter();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if ("student".equals(name)) {
                writer.write(name);
                return;
            }
        }
        writer.write("administer");
    }
}
