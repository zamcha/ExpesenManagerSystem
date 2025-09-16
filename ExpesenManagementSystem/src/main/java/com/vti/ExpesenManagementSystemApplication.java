package com.vti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ExpesenManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpesenManagementSystemApplication.class, args);
	}

	 @Bean
	    WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurer() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**")
	                        .allowedOrigins("http://localhost:3000") // domain frontend, có thể thêm nhiều domain
	                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
	                        .allowedHeaders("*")
	                        .allowCredentials(true); // cho phép gửi cookie/token
	            }
	        };
	    }

}
