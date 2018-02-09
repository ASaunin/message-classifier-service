package com.asaunin.classifier.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ConfigurationProperties("api")
@EnableSwagger2
@Setter
public class SwaggerConfig {

    private String version;
    private String name;
    private String description;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.asaunin.classifier"))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public UiConfiguration getConfig(){
        //Validation is disabled to prevent Cors error when app is deployed to Heroku
        //For the better solution add security configuration with cors filter implementation
        return UiConfigurationBuilder.builder()
                .validatorUrl("")
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(name)
                .description(description)
                .contact(new Contact("Alex Saunin", "https://github.com/ASaunin", ""))
                .version(version)
                .build();
    }

}