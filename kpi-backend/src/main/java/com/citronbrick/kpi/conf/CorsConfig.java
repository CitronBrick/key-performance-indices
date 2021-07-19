package com.citronbrick.kpi.conf;

import org.springframework.web.servlet.config.annotation.*;
import org.springframework.context.annotation.*;


@Configuration
public class CorsConfig {

	@Bean 
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry reg) {
				reg.addMapping("/**").allowedOrigins("*").allowedMethods("GET","POST","HEAD","OPTIONS","PUT","DELETE");
			}
		};
	}
}