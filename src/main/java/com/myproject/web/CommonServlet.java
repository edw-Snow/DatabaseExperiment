package com.myproject.web;

import com.alibaba.fastjson.JSON;
import com.myproject.pojo.Course;
import com.myproject.service.impl.AdministorServiceImpl;
import com.myproject.service.impl.CommonServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/common/*")
public class CommonServlet extends BaseServlet {
    private CommonServiceImpl commonService = new CommonServiceImpl();

    public void selectCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Course> courses = commonService.selectCourse();
        String jsonString = JSON.toJSONString(courses);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
