package com.notificationappservice.contactservice.contactservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;


/**
 * User: edward <br/>
 * Date: 11/25/18 7:50 AM <br/>
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //http://localhost:9090/swagger-ui.html
    //http://localhost:9090/v2/api-docs
    @Bean
    public Docket api() {
        HashSet<String> consumesAndProduces =
                new HashSet<String>(Arrays.asList("application/json"));
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metadata())
                .consumes(consumesAndProduces)
                .produces(consumesAndProduces)
                .pathMapping("/");
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("Go-Student CAS API")
                .description("Go-Student : Course Assignment Service")
                .version("1.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}