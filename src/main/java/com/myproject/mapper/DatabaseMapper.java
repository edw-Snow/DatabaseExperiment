package com.myproject.mapper;

import com.myproject.pojo.*;
import com.sun.scenario.effect.Crop;
import org.apache.ibatis.annotations.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface DatabaseMapper {
    @Select("select * from experiment.administer  where username=#{username} and password=#{password}")
    User loginAdministerTest(@Param("username") String username, @Param("password") String password);

    @Select("select * from experiment.studentuser where username=#{username} and password=#{password}")
    User loginStudentTest(@Param("username") String username, @Param("password") String password);

    //搜索总行数
    @Select("select count(*) from experiment.${tableName};")
    int selectTotalCount(@Param("tableName") String tableName);

    //分页查询学生表
    @Select("select * from experiment.student limit #{begin},#{size};")
    List<Student> selectStudentByPage(@Param("begin") int begin, @Param("size") int size);

    //分页查询教师表
    @Select("select * from experiment.teacher limit #{begin},#{size} ;")
    List<Teacher> selectTeacherByPage(@Param("begin") int begin, @Param("size") int size);

    //分页查询课程表
    @Select("select * from experiment.course limit #{begin},#{size} ;")
    List<Course> selectCrouseByPage(@Param("begin") int begin, @Param("size") int size);

    @Insert("insert into experiment.student values (#{sid},#{name},#{gender},#{age});")
    int addStudent(Student student);

    @Insert("insert into experiment.teacher values (#{tid},#{name},#{position},#{salary});")
    int addTeacher(Teacher teacher);

    @Insert("insert into experiment.course values (#{cid},#{name},#{credits},#{tid});")
    int addCourse(Course course);

    //修改学生登录信息
    @Update("update experiment.studentuser set password = #{password} where username =#{username};")
    void updataStudentMessage(@Param("username") String username, @Param("password") String password);

    //复选框选中批量删除表数据
    void deleteByIds(@Param("ids") int[] ids, @Param("tableName") String tableName, @Param("idName") String idName);

    //修改学生数据
    @Update("update experiment.student set name = #{name},gender=#{gender},age=#{age} where sid=#{sid};")
    void updateStudent(Student student);

    //删除一行学生数据
    @Delete("delete from experiment.student where sid = #{id};")
    void deleteStudentByRow(int id);

    //查询课程表信息
    @Select("select * from experiment.course;")
    List<Course> selectCourse();

    //统计不同职称的老师数量，薪资
    @Select("select count(*) as numberOfPosition,position,AVG(salary) AS AVGSalaryOfPosition from experiment.teacher group by position order by AVGSalaryOfPosition desc ;")
    List<PositionStat> selectPositionStat();

    //统计每个老师所教课程的平均成绩，最高分，最低分
    @Select("SELECT t.tid as tid, t.name as name, c.name as cname, AVG(s.score) AS avgScore, MAX(s.score) AS maxScore, " +
            "MIN(s.score) AS minScore FROM experiment.teacher t JOIN experiment.course c " +
            "ON t.tid = c.tid JOIN experiment.score s ON c.cid = s.cid GROUP BY t.tid, c.cid;")
    List<TeacherStat> selectTeacherStat();

    //学生查询课程选修成绩
    @Select("select student.sid ,student.name as sname,stuScore.cid,course.name as cname,stuScore.score from " +
            "experiment.student as student,experiment.score as stuScore," +
            "experiment.course as course where student.sid=stuScore.sid and stuScore.cid=" +
            "course.cid and student.name= #{stuName}")
    @ResultMap("StudentStatResultMap")
    List<StudentStat> selectStudentScore(@Param("stuName") String name);

    //管理员身份条件查询学生表
    List<Student> selectStudentByPageAndCondition(@Param("oneStudent") Student student, @Param("begin") int begin, @Param("size") int size);
    //管理身份条件查询数量
    int selectStudentTotalCountByCondition(@Param("oneStudent") Student student);
    //学生选修课程
    @Insert("INSERT INTO experiment.score (sid, cid)\n" +
            "SELECT sid, cid\n" +
            "FROM experiment.student,experiment.course WHERE course.name = #{courseName}\n" +
            "AND student.name = #{studentName};")
    int addSelectiveCourse(@Param("courseName") String courseName,@Param("studentName")String studentName);
    //退选课程
    @Delete("delete from experiment.score where sid=#{sid} and cid= #{cid};")
    void handleStudentDelete(@Param("sid")int sid,@Param("cid")int cid);
    //查询选修的总学分
    @Select("SELECT SUM(credits) FROM experiment.score,experiment.course,experiment.student WHERE student.sid=score.sid AND score.cid= course.cid AND\n" +
            "student.name =#{name}")
    Float getTotalScore(String name);
}
