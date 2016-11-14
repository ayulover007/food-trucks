package com.hhl.foodtruck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EnableConfigurationProperties
@PropertySources({
		@PropertySource("classpath:/config/app/app-common.properties"),
		@PropertySource("classpath:/config/app/app-${spring.profiles.active}.properties") })
public class App {

	public static void main(String[] args) {
		if (System.getProperty("spring.profiles.active") == null) {
			System.setProperty("spring.profiles.active", "dev");
		}
		SpringApplication.run(App.class, args);
	}
	
}
