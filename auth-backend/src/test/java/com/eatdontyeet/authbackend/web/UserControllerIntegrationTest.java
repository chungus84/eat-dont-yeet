package com.eatdontyeet.authbackend.web;

import com.eatdontyeet.authbackend.entity.User;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

import javax.print.attribute.standard.Media;
import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String authorizationToken;

    @Test
    @DisplayName("User can be created")
    @Order(1)
    void testSaveUser_WhenGivenValidDetails_ReturnsUsersDetails() throws Exception {
        JSONObject userDetailsRequestJSON = new JSONObject();

        userDetailsRequestJSON.put("firstName", "Test");
        userDetailsRequestJSON.put("lastName", "Testy");
        userDetailsRequestJSON.put("userName", "TestyTest");
        userDetailsRequestJSON.put("email", "test@test.com");
        userDetailsRequestJSON.put("password", "passtest");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(userDetailsRequestJSON.toString(), headers);

        // Act
        ResponseEntity<User> createdUserEntity = testRestTemplate.postForEntity("/users/register", request, User.class);

        HttpHeaders responseHeaders = createdUserEntity.getHeaders();

        // Assert
        assertEquals(HttpStatus.CREATED, createdUserEntity.getStatusCode());

    }
}
