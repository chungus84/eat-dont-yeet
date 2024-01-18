package com.eatdontyeet.authbackend.web;

import com.eatdontyeet.authbackend.entity.User;
import com.eatdontyeet.authbackend.security.SecurityContants;
import com.eatdontyeet.authbackend.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

import javax.print.attribute.standard.Media;
import java.util.Arrays;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserService userService;

    private String authorizationToken;

    private String userId ;

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
        userId = responseHeaders.getFirst("userId");
        System.out.println(userId);


        // Assert
        assertEquals(HttpStatus.CREATED, createdUserEntity.getStatusCode());
        assertEquals(userDetailsRequestJSON.get("userName"), responseHeaders.getFirst("userName"));
        assertNotNull(responseHeaders.get("userId"));

    }

    @Test
    @DisplayName("GET /users/{id} requires JWT")
    @Order(2)
    void testFindUserById_WhenMissingJWT_Returns403() {

        // Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");


        HttpEntity requestEntity = new HttpEntity(null, headers);

        // Act
        ResponseEntity<String> response = testRestTemplate.exchange("/users/" + userId,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
    @Test
    @DisplayName("login user")
    @Order(3)
    void testFindByUserId_WhenValidCredentialsProvided_returnsJWTinAuthorizationHeaders() throws JSONException {

        // Arrange
        JSONObject loginCredentials = new JSONObject();
        loginCredentials.put("email", "test@test.com");
        loginCredentials.put("password", "passtest");

        HttpEntity<String> request = new HttpEntity<>((loginCredentials.toString()));


        ResponseEntity response = testRestTemplate.postForEntity("/authenticate",
                request,
                null);

        authorizationToken = response.getHeaders().getValuesAsList(SecurityContants.AUTHORIZATION).get(0);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getHeaders().getValuesAsList(SecurityContants.AUTHORIZATION).get(0));
        assertNotNull(response.getHeaders().getValuesAsList("userId").get(0));
    }

    @Test
    @Order(4)
    @DisplayName("GET /users/{id} works")
    void testFindByUserId_WhenValidJWTProvided_returnsUser() {

        // Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(authorizationToken);

        HttpEntity requestEntity = new HttpEntity<>(headers);

        // Act
        ResponseEntity<String> response = testRestTemplate.exchange("/users/" + userId,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<String>() {

                });

//        System.out.println(response.getBody());

        assertEquals(HttpStatus.OK,
                response.getStatusCode(),
                "Http status code should be 200");

    }


}
