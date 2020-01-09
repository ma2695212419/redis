package co.laomag.redislogindemo.dao;

import co.laomag.redislogindemo.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 马泽朋
 * @version 1.0
 * @date 2020/1/4 下午 1:58
 */
@Mapper
public interface StudentDao {
    Student loginByPwd(@Param("sid") String sid, @Param("pwd") String pwd);
}
