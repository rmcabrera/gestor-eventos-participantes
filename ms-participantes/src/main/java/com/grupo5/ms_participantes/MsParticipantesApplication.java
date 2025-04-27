package com.grupo5.ms_participantes;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(title = "API Eventos", version = "v1", description = "Gestión de eventos"))
public class MsParticipantesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsParticipantesApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/");
				registry.addResourceHandler("/v3/api-docs/**").addResourceLocations("classpath:/META-INF/resources/");
			}
		};
	}
}
