package com.example.Proj1_2021202057;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
@EnableJpaAuditing
public class Proj12021202057Application {

	public static void main(String[] args) {
		SpringApplication.run(Proj12021202057Application.class, args);
	}

	@Bean
	public HiddenHttpMethodFilter httpMethodFilter(){
		return new HiddenHttpMethodFilter();
	}
}