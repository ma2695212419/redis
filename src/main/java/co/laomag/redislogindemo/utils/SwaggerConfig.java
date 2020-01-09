package co.laomag.redislogindemo.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author 马泽朋
 * @version 1.0
 * @date 2020/1/7 上午 10:28
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig{
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("co.laomag.redislogindemo.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("富文本上传")
                .description("服务协议等问题的持久化")
                .contact(new Contact("马泽朋",
                        "http://www.laomag.co",
                        "2695212419@qq.com"))
                .build();
    }

}
