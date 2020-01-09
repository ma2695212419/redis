package co.laomag.redislogindemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = "co.laomag.redislogindemo.dao")
public class RedisLoginDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisLoginDemoApplication.class, args);
    }

}
