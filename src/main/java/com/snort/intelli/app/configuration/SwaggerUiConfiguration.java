package com.snort.intelli.app.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Slf4j
@EnableWebMvc
@EnableSwagger2
public class SwaggerUiConfiguration {

    //1. give your app info
    private ApiInfo getApiInfo(){
        log.info("ApiInfo built successful!");
        return new ApiInfoBuilder()
                .title("Todos Rest api")
                .description("Todos Rest api is developed to satisfy Task management application.")
                .version("version - 1.0")
                .license("licence provided by : snort-licence")
                .contact(
                        new Contact("Siddhartha Sharma",
                                "snortintelligence.in",
                                "snortintelligence@gmail.com")).
                build();
    }

    //2. Docket Bean
    @Bean
    public Docket getDocket(){
        log.info("Docket is creating...");
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(getApiInfo())
            .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }


}
