package com.myproject.service.impl;

import com.myproject.mapper.DatabaseMapper;
import com.myproject.pojo.*;
import com.myproject.service.OperateService;
import com.myproject.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class AdministorServiceImpl implements OperateService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    //分页查询学生信息
    public PageBean<Student> selectStudentByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = factory.openSession();
        DatabaseMapper mapper = sqlSession.getMapper(DatabaseMapper.class);
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        List<Student> rows = mapper.selectStudentByPage(begin, size);
        int totalCount = mapper.selectTotalCount("student");
        PageBean<Student> studentPageBean = new PageBean<Student>();
        studentPageBean.setRows(rows);
        studentPageBean.setTotalCount(totalCount);
        sqlSession.close();
        return studentPageBean;
    }

    public PageBean<Teacher> selectTeacherByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = factory.openSession();
        DatabaseMapper mapper = sqlSession.getMapper(DatabaseMapper.class);
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        List<Teacher> rows = mapper.selectTeacherByPage(begin, size);
        int totalCount = mapper.selectTotalCount("teacher");
        PageBean<Teacher> teacherPageBean = new PageBean<Teacher>();
        teacherPageBean.setRows(rows);
        teacherPageBean.setTotalCount(totalCount);
        sqlSession.close();
        return teacherPageBean;
    }


    public void updataStudentMessage(String username, String password) {
        SqlSession sqlSession = factory.openSession();
        DatabaseMapper mapper = sqlSession.getMapper(DatabaseMapper.class);
        mapper.updataStudentMessage(username, password);
        sqlSession.commit();
        sqlSession.close();
    }

    //添加学生信息
    public int addStudent(Student student) {
        SqlSession sqlSession = null;
        try {
            sqlSession = factory.openSession();
            DatabaseMapper mapper = sqlSession.getMapper(DatabaseMapper.class);
            int sucess = mapper.addStudent(student);
            sqlSession.commit();
            return sucess;
        } catch (Exception e) {
            return -1;
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    public int addTeacher(Teacher teacher) {
        SqlSession sqlSession = null;
        try {
            sqlSession = factory.openSession();
            DatabaseMapper mapper = sqlSession.getMapper(DatabaseMapper.class);
            int success = mapper.addTeacher(teacher);
            sqlSession.commit();
            sqlSession.close();
            return success;
        } catch (Exception e) {
            return -1;
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    //批量删除学生信息
    public void deleteByIds(int[] ids, String tableName) {
        SqlSession sqlSession = factory.openSession();
        DatabaseMapper mapper = sqlSession.getMapper(DatabaseMapper.class);
        if ("student".equals(tableName)) {
            mapper.deleteByIds(ids, tableName, "sid");
        } else if ("teacher".equals(tableName)) {
            mapper.deleteByIds(ids, tableName, "tid");
        } else if ("course".equals(tableName)) {
            mapper.deleteByIds(ids, tableName, "cid");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    //修改学生信息
    public void updateStudent(Student student) {
        SqlSession sqlSession = factory.openSession();
        DatabaseMapper mapper = sqlSession.getMapper(DatabaseMapper.class);
        mapper.updateStudent(student);
        sqlSession.commit();
        sqlSession.close();
    }

    public void deleteStudentByRow(int id) {
        SqlSession sqlSession = factory.openSession();
        DatabaseMapper mapper = sqlSession.getMapper(DatabaseMapper.class);
        mapper.deleteStudentByRow(id);
        sqlSession.commit();
        sqlSession.close();
    }


    public List<PositionStat> selectPositionStat() {
        SqlSession sqlSession = factory.openSession();
        DatabaseMapper mapper = sqlSession.getMapper(DatabaseMapper.class);
        List<PositionStat> positionStats = mapper.selectPositionStat();
        sqlSession.close();
        return positionStats;
    }

    public List<TeacherStat> selectTeacherStat() {
        SqlSession sqlSession = factory.openSession();
        DatabaseMapper mapper = sqlSession.getMapper(DatabaseMapper.class);
        List<TeacherStat> teacherStats = mapper.selectTeacherStat();
        sqlSession.close();
        return teacherStats;
    }

    //管理员身份条件模糊查询学生表
    public PageBean<Student> selectStudentByPageAndCondition(int currentPage, int pageSize, Student student) {
        SqlSession sqlSession = factory.openSession();
        DatabaseMapper mapper = sqlSession.getMapper(DatabaseMapper.class);
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        List<Student> rows = mapper.selectStudentByPageAndCondition(student, begin, size);
        int totalCount = mapper.selectStudentTotalCountByCondition(student);
        PageBean<Student> studentPageBean = new PageBean<Student>();
        studentPageBean.setRows(rows);
        studentPageBean.setTotalCount(totalCount);
        sqlSession.close();
        return studentPageBean;
    }

}
