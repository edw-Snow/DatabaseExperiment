package com.myproject.service.impl;

import com.myproject.mapper.DatabaseMapper;
import com.myproject.pojo.Course;
import com.myproject.service.OperateService;
import com.myproject.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class CommonServiceImpl implements OperateService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    public List<Course> selectCourse() {
        SqlSession sqlSession = factory.openSession();
        DatabaseMapper mapper = sqlSession.getMapper(DatabaseMapper.class);
        List<Course> courses = mapper.selectCourse();
        sqlSession.close();
        return courses;
    }
}
