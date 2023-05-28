package com.myproject.web;

import com.alibaba.fastjson.JSON;
import com.myproject.pojo.*;
import com.myproject.service.OperateService;
import com.myproject.service.impl.AdministorServiceImpl;
import com.myproject.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/administer/*")
public class AdministerServlet extends BaseServlet {
    private AdministorServiceImpl adminsterService = new AdministorServiceImpl();

    //分页查询学生表
    public void selectStudentByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String _currentPage = request.getParameter("currentPage");
        String _size = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int size = Integer.parseInt(_size);
        PageBean<Student> studentPageBean = adminsterService.selectStudentByPage(currentPage, size);
        String jsonString = JSON.toJSONString(studentPageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectTeacherByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String _currentPage = request.getParameter("currentPage");
        String _size = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int size = Integer.parseInt(_size);
        PageBean<Teacher> teacherPageBean = adminsterService.selectTeacherByPage(currentPage, size);
        String jsonString = JSON.toJSONString(teacherPageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //添加学生
    public void addStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Student student = JSON.parseObject(params, Student.class);
        int sucess = adminsterService.addStudent(student);
        response.getWriter().write(Integer.toString(sucess));
    }

    public void addTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String readLine = reader.readLine();
        Teacher teacher = JSON.parseObject(readLine, Teacher.class);
        int success = adminsterService.addTeacher(teacher);
        response.getWriter().write(Integer.toString(success));
    }

    //更新学生登录信息
    public void updataStudentMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String readLine = reader.readLine();
        User user = JSON.parseObject(readLine, User.class);
        String username = user.getUsername();
        String password = user.getPassword();
        adminsterService.updataStudentMessage(username, password);
        response.getWriter().write("success");
    }

    //批量删除学生
    public void deleteByStudentIds(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String readLine = reader.readLine();
        int[] ids = JSON.parseObject(readLine, int[].class);
        adminsterService.deleteByIds(ids, "student");
        response.getWriter().write("success");
    }

    public void deleteByTeacherIds(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String readLine = reader.readLine();
        int[] ids = JSON.parseObject(readLine, int[].class);
        adminsterService.deleteByIds(ids, "teacher");
        response.getWriter().write("success");
    }

    public void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String readLine = reader.readLine();
        Student student = JSON.parseObject(readLine, Student.class);
        adminsterService.updateStudent(student);
        response.getWriter().write("success");
    }

    public void deleteStudentByRow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String readLine = reader.readLine();
        Integer integer = JSON.parseObject(readLine, int.class);
        adminsterService.deleteStudentByRow(integer);
        response.getWriter().write("success");
    }

    public void selectPositionStat(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<PositionStat> positionStats = adminsterService.selectPositionStat();
        String jsonString = JSON.toJSONString(positionStats);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectTeacherStat(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<TeacherStat> teacherStats = adminsterService.selectTeacherStat();
        String jsonString = JSON.toJSONString(teacherStats);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectStudentByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //url数据
        String _currentPage = request.getParameter("currentPage");
        String _size = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int size = Integer.parseInt(_size);
        //json数据
        BufferedReader reader = request.getReader();
        String readLine = reader.readLine();
        Student student = JSON.parseObject(readLine, Student.class);
        PageBean<Student> studentPageBean = adminsterService.selectStudentByPageAndCondition(currentPage, size, student);
        String jsonString = JSON.toJSONString(studentPageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}

