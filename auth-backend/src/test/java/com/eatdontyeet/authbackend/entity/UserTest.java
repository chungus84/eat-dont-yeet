package com.eatdontyeet.authbackend.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User Entity Tests")
public class UserTest {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;

    @BeforeEach
    void setup() {
        firstName = "Test";
        lastName = "Testerson";
        userName = "Testtest";
        email = "test@test.com";
        password = "passtest";
    }

    @DisplayName("Creates a new User Entity")
    @Test
    void testUserEntity_WhenGivenUserDetails_ShouldReturnANewUser() {

        // Arrange & Act
        User user = new User(firstName, lastName, userName, email, password);

        System.out.println(user.getCreatedAt());
        System.out.println(user.getId());

        // Assert
        assertEquals(firstName, user.getFirstName(), "Returned firstName was different from expected");
        assertEquals(lastName, user.getLastName(), "Returned lastName was different from expected ");
        assertEquals(email, user.getEmail(), "Returned email was different from expected ");
        assertEquals(userName, user.getUserName(), "Returned userName was different from expected ");
        assertEquals(password, user.getPassword(), "Returned lastName was different from expected ");
        assertNull(user.getId(), "Returned Id should be null");
        assertEquals(LocalDate.now(), user.getCreatedAt());


    }
    @DisplayName("Creates Another UserEntity")
    @Test
    void testUserEntity_WhenGivenUserDetails_ShouldReturnAnotherUser() {

        // Arrange & Act
        User testUser = new User();
        testUser.setFirstName(firstName);
        testUser.setLastName(lastName);
        testUser.setEmail(email);
        testUser.setUserName(userName);
        testUser.setPassword(password);

        // Assert
        assertEquals(firstName, testUser.getFirstName(),"Returned firstName was different from expected");
        assertEquals(lastName, testUser.getLastName(), "Returned lastName was different from expected ");
        assertEquals(email, testUser.getEmail(), "Returned email was different from expected ");
        assertEquals(userName, testUser.getUserName(), "Returned userName was different from expected ");
        assertEquals(password, testUser.getPassword(), "Returned lastName was different from expected ");
        assertNull(testUser.getId(), "Returned Id should be null");
        assertEquals(LocalDate.now(), testUser.getCreatedAt());



    }


}
