package com.eatdontyeet.authbackend;

import com.eatdontyeet.authbackend.service.JwtGenerator;
import com.eatdontyeet.authbackend.service.JwtGeneratorImpl;
import com.eatdontyeet.authbackend.shared.SpringApplicationContext;
import com.eatdontyeet.authbackend.shared.UserDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;


@SpringBootApplication
public class AuthBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthBackendApplication.class, args);
	}


	@Bean
	public JwtGeneratorImpl jwtGenerator() {
		return new JwtGeneratorImpl();
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

}
