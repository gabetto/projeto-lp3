package com.JavaRunner.JavaRunner;

import com.JavaRunner.JavaRunner.controller.auth.AdminFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class JavaRunnerApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(JavaRunnerApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminFilter()).addPathPatterns("/admin/*");
    }
}
