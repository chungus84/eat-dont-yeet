package com.eatdontyeet.authbackend.web;

import com.eatdontyeet.authbackend.entity.User;
import com.eatdontyeet.authbackend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = UserController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class })
public class UserControllerWebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    private User user;


    @MockBean
    UserService userService;

    @BeforeEach
    void init() {
        user = new User("Test", "Testerson", "testtest", "test@test.com", "passtest");
    }

    @DisplayName("User can be created")
    @Test
    void testSaveUser_WhenGivenValidDetails_ShouldReturnCreatedUserDetails() throws Exception {

        // Arrange
        when(userService.saveUser(any(User.class))).thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(user));

        // Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse headers = mvcResult.getResponse();

        // Assert
        assertEquals(user.getUserName(), headers.getHeader("userName"), "The returned UserName is incorrect");
        assertEquals(user.getUserId(), headers.getHeader("userId"), "The returned userId is incorrect");

    }

    @DisplayName("First Name is not empty")
    @Test
    void testSaveUser_WhenFirstNameIsNotProvided_returns400StatusCode() throws Exception {

        // Arrange
        user.setFirstName("");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(user));

        // Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus(), "Incorrect Http status returned");
    }

    @DisplayName("Returns user based on userId")
    @Test
    void testFindUserById_WhenGivenAValidUserID_ShouldReturnAUserName() throws Exception {
        // Arrange
        when(userService.getUserByUserId(any(String.class))).thenReturn(user);
        String userId = user.getUserId();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/" + userId);

        // Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        // Assert
        assertEquals(user.getUserName(), mvcResult.getResponse().getContentAsString());
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());


    }

}
