package com.eatdontyeet.authbackend.service;

import com.eatdontyeet.authbackend.entity.User;
import com.eatdontyeet.authbackend.exception.EntityNotFoundException;
import com.eatdontyeet.authbackend.repository.UserRepository;
import com.eatdontyeet.authbackend.shared.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("UserService Tests")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;



    private User user1;
    private User user2;


    @BeforeEach
    void setup() {
        user1 = new User();
        user1.setFirstName("Test");
        user1.setLastName("Testerson");
        user1.setUserName("Testtest");
        user1.setEmail("test@test.com");
        user1.setPassword("password");


        user2 = new User("Dave", "Daverson", "davedave", "dave@dave.com", "passdave");

    }

//    @Disabled
//    @DisplayName("saveUser Test")
//    @Test
//    void testSaveUser_whenGivenUserDetails_ShouldReturnUserObjectAndCallUserRepoSave() {
//
//        // Arrange
//        when(userRepository.save(any(User.class))).thenReturn(user1);
//        when(bCryptPasswordEncoder.encode(any(String.class))).thenReturn("password");
//
//        // Act
//        User savedUser = userService.saveUser(user1);
//
//        // Assert
//        assertEquals(user1.getUserName(), savedUser.getUserName(), "Returned UserName was different than expected");
//        verify(userRepository, times(1)).save(user1);
//        verify(bCryptPasswordEncoder, times(1)).encode(user1.getPassword());
//
//    }

//    @DisplayName("Another saveUser Test")
//    @Test
//    void testSaveUser_WhenGivenUser2Details_ShouldReturnUserObjectAndCallUserRepoSave(){
//        // Arrange
//        when(userRepository.save(any(User.class))).thenReturn(user2);
//        when(bCryptPasswordEncoder.encode(any(String.class))).thenReturn("passdave");
//
//        // Act
//        User savedUser = userService.saveUser(user2);
//
//        // Assert
//        assertEquals(user2.getUserName(), savedUser.getUserName(), "Returned UserName was different than expected");
//        verify(userRepository, times(1)).save(user2);
//        verify(bCryptPasswordEncoder, times(1)).encode(user2.getPassword());
//    }

    @DisplayName("Should throw exception if user data is empty")
    @Test
    void testSaveUser_WhenGivenInvalidUserDetails_ShouldThrowIllegalArgumentException() {

        // Arrange
        user2.setFirstName("");
        String expectedExceptionString = "User details are incomplete";

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, ()-> {
            userService.saveUser(user2);
        }, "Empty and Blank fields should have thrown exception");

        assertEquals(expectedExceptionString, thrown.getMessage());
    }

    @Disabled
    @DisplayName("getUser Should return user with a valid UserId")
    @Test
    void testGetUser_WhenGivenAValidUserId_ShouldReturnTheCorrectUser(){

        // Arrange
        String userId = user2.getUserId();
        when(userRepository.findByUserId(any(String.class))).thenReturn(Optional.of(user2));

        // Act
        UserDto storedUser = userService.getUserByUserId(userId);

        // Assert
        assertEquals(user2.getUserName(), storedUser.getUserName());
        verify(userRepository,times(1)).findByUserId(userId);
    }

    @DisplayName("get User Should throw an EntityNotFoundException")
    @Test
    void testGetUser_WhenGivenAnInvalidUserId_ShouldThrowEntityNotFoundException() {

        // Arrange
        String badUserId = "123";
        String expectedExceptionMessage = "The user with id 123 does not exist in our records";

        // Act & Assert
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, ()-> {
            userService.getUserByUserId(badUserId);
        }, "Should have thrown an EntityNotFoundException");

        assertEquals(expectedExceptionMessage, thrown.getMessage(), "Exception message was not as expected");
    }

    @DisplayName("loginUser should return user object")
    @Test
    void testFindUserByEmail_WhenGivenCorrectCredentials_ShouldReturnUser() {

        // Arrange
        String testEmail = "test@test.com";

    }

}
