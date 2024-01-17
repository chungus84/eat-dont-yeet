package com.eatdontyeet.authbackend.service;

import com.eatdontyeet.authbackend.entity.User;
import com.eatdontyeet.authbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @DisplayName("saveUser Test")
    @Test
    void testSaveUser_whenGivenUserDetails_ShouldReturnUserObjectAndCallUserRepoSave() {



        when(userRepository.save(any(User.class))).thenReturn(user1);
        when(bCryptPasswordEncoder.encode(any(String.class))).thenReturn("password");

        User savedUser = userService.saveUser(user1);


        assertEquals(user1.getUserName(), savedUser.getUserName(), "Returned UserName was different than expected");
        verify(userRepository, times(1)).save(user1);


    }


}
