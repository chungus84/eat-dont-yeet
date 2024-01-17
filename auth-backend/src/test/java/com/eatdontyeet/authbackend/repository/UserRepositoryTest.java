package com.eatdontyeet.authbackend.repository;

import com.eatdontyeet.authbackend.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@DisplayName("User Repository Tests")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
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
        testEntityManager.persistAndFlush(user1);

        user2 = new User("Dave", "Daverson", "davedave", "dave@dave.com", "passdave");
        testEntityManager.persistAndFlush(user2);
    }

    @DisplayName("Should find a user by email")
    @Test
    void testFindByEmail_WhenGivenCorrectEmailDetails_ShouldReturnAUser() {

        // Act
        Optional<User> storedUser = userRepository.findByEmail(user1.getEmail());

        // Assert
        assertTrue(storedUser.isPresent(), "User Entity should be present");
        assertEquals(storedUser.get().getUserName(), user1.getUserName(), "Returned UserName is different from expected");
    }

}
