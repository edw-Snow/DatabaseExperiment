package com.myproject.service.impl;

import com.myproject.mapper.DatabaseMapper;
import com.myproject.pojo.PageBean;
import com.myproject.pojo.Student;
import com.myproject.pojo.StudentStat;
import com.myproject.service.OperateService;
import com.myproject.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class StudentServiceImpl implements OperateService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<StudentStat> selectStudentScore(String name) {
        SqlSession sqlSession = factory.openSession();
        DatabaseMapper mapper = sqlSession.getMapper(DatabaseMapper.class);
        List<StudentStat> studentStats = mapper.selectStudentScore(name);
        sqlSession.close();
        return studentStats;
    }

    //学生选修课程
    public int addSelectiveCourse(String courseName, String studentName) {
        SqlSession sqlSession = null;
        try {
            sqlSession = factory.openSession();
            DatabaseMapper mapper = sqlSession.getMapper(DatabaseMapper.class);
            int success = mapper.addSelectiveCourse(courseName, studentName);
            sqlSession.commit();
            return success;
        } catch (Exception e) {
            return -1;
        } finally {
            if (sqlSession != null)
                sqlSession.close();
        }
    }
    public void handleStudentDelete(int sid, int cid){
        SqlSession sqlSession = factory.openSession();
        DatabaseMapper mapper = sqlSession.getMapper(DatabaseMapper.class);
        mapper.handleStudentDelete(sid,cid);
        sqlSession.commit();
        sqlSession.close();
    }
    public Float getTotalScore(String name){
        SqlSession sqlSession = factory.openSession();
        DatabaseMapper mapper = sqlSession.getMapper(DatabaseMapper.class);
        Float totalScore = mapper.getTotalScore(name);
        sqlSession.close();
        return totalScore;
    }
}
