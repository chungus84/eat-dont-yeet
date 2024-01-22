package com.eatdontyeet.recipebackend;

import com.eatdontyeet.recipebackend.service.ExternalApiServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RecipeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeBackendApplication.class, args);
	}

	@Bean
	public ExternalApiServiceImpl externalApiService() {
		return new ExternalApiServiceImpl();
	}

}
