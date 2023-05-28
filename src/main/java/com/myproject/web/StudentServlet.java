package com.myproject.web;

import com.alibaba.fastjson.JSON;
import com.myproject.pojo.Student;
import com.myproject.pojo.StudentStat;
import com.myproject.pojo.User;
import com.myproject.service.impl.AdministorServiceImpl;
import com.myproject.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.AudioFormat;
import java.io.IOException;
import java.util.List;

@WebServlet("/student/*")
public class StudentServlet extends BaseServlet {
    private StudentServiceImpl studentServiceImpl = new StudentServiceImpl();

    public void selectStudentScore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User student = (User) session.getAttribute("student");
        List<StudentStat> studentStats = studentServiceImpl.selectStudentScore(student.getUsername());
        String jsonString = JSON.toJSONString(studentStats);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void addSelectiveCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User student = (User) session.getAttribute("student");
        String username = student.getUsername();
        String str = request.getParameter("courseName");
        byte[] bytes = str.getBytes("ISO-8859-1");
        String courseName = new String(bytes, "utf-8");
        int flag = studentServiceImpl.addSelectiveCourse(courseName, username);
        response.getWriter().write(Integer.toString(flag));
    }

    public void handleStudentDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String _sid = request.getParameter("sid");
        String _cid = request.getParameter("cid");
        int sid = Integer.parseInt(_sid);
        int cid = Integer.parseInt(_cid);
        studentServiceImpl.handleStudentDelete(sid, cid);
        response.getWriter().write("success");
    }

    public void getTotalScore(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User student = (User) session.getAttribute("student");
        String name = student.getUsername();
        Float totalScore = studentServiceImpl.getTotalScore(name);
        response.getWriter().write(Float.toString(totalScore));
    }
}