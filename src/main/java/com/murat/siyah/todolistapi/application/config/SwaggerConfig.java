package com.murat.siyah.todolistapi.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Map;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(Map.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(applicationName)
                .build();
    }

}