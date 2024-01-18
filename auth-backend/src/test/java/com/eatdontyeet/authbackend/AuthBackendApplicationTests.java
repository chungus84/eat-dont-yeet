package com.eatdontyeet.authbackend;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;

@Disabled
@SpringBootTest
class AuthBackendApplicationTests {

	@Test
	void contextLoads() {
	}

	@Bean
	public TestRestTemplate testRestTemplate() {return new  TestRestTemplate();}

}
