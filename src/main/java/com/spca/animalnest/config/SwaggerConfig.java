package com.spca.animalnest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * @author seenline
 * @data 2021/8/23 20:15
 * @description swagger的配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo("动物小窝",
                        "动物小窝的帖子接口",
                        "v0.0.1",
                        "about:blank",
                        new Contact("seenline","about:blank","@qq.com"),
                        "Apache 2.0",
                        "http://www.apache.org/licenses/LICENSE-2.0",
                        new ArrayList()))
                .groupName("帖子类接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.spca.animalnest.controller"))
                .build();


    }
}
