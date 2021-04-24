package com.example.as.api.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * Swagger配置文件
 * 通过http://localhost:你的端口号/swagger-ui.html 即可访问查看效果
 * http://localhost:5088/doc.html
 * 没有该类Swagger默认也是可以访问的
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String VERSION  = "1.0";
    private static final String AUTHOR  = "顺风";
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("接口文档")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.as.api.controller"))
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(ApiIgnore.class)
                .enableUrlTemplating(false)
                .tags(new Tag("Category","商品类别"))
                .tags(new Tag("City","省市区"))

                .tags(new Tag("Account","账号模块"));
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("API接口文档")
                .description("111111")
                .version(VERSION)
                .contact(new Contact(AUTHOR,"http://blog.lishunfneg.top","mac2020@126.com"))
                .build();
    }

}
