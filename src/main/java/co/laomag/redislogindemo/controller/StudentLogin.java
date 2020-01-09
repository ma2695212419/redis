package co.laomag.redislogindemo.controller;

import cn.hutool.http.Header;
import cn.hutool.json.JSONUtil;
import co.laomag.redislogindemo.dao.StudentDao;
import co.laomag.redislogindemo.pojo.Result;
import co.laomag.redislogindemo.pojo.Student;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author 马泽朋
 * @version 1.0
 * @date 2020/1/4 下午 2:08
 */
@Controller
@Api(value = "学生登录",tags = {"急急急急急急"})
public class StudentLogin {
    @Resource
    private RedisTemplate stringRedisTemplate;

    @Autowired
    private StudentDao studentDao;
    @ApiOperation(value = "用来登录的方法",notes = "登录成功返回学生数据",httpMethod = "post",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid",value = "sid",dataType = "string",example = "1"),
            @ApiImplicitParam(name = "pwd",value = "pwd",dataType = "string",example = "1111111")
    })
    @ApiResponses({
            @ApiResponse(code = 2001,message = "登录信息有误！"),
            @ApiResponse(code = 1001,message = "成功")
    })
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result loginByPwd(Student student, Model model){
        Result result = new Result();
        Student student1 = studentDao.loginByPwd(student.getSid(), student.getPwd());
        if(student1==null){
            result.setCode(2001);
            result.setMessage("登录失败");
            return result;
        }else {
            JwtBuilder builder = Jwts.builder().setId(student1.getSid())
                    .setHeaderParam("type", "token")
                    .setSubject(student1.getSid())
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256,"213244");
            String compact = builder.compact();

            result.setCode(1001);
            result.setMessage("登录成功");
            result.setData(compact);
            this.setRedis(compact,student1);
            return result;
        }

    }

    private boolean setRedis(String compact,Student student){
        System.out.println(compact);

        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        Boolean persist = stringRedisTemplate.hasKey(student.getSid());
        System.out.println(persist);
        if(persist){
            stringRedisTemplate.delete(ops.get(student.getSid()));
            stringRedisTemplate.delete(student.getSid());
        }
        //System.out.println(compact);
        ops.set(student.getSid(),compact);
        ops.set(compact, JSONUtil.toJsonPrettyStr(student));
        return true;
    }
}
