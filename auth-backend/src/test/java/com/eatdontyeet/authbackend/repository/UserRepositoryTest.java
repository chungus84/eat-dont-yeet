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
        assertEquals(storedUser.get().getFirstName(), user1.getFirstName(), "Returned FirstName is different from expected");
        assertEquals(storedUser.get().getLastName(), user1.getLastName(), "Returned LastName is different from expected");
        assertEquals(storedUser.get().getEmail(), user1.getEmail(), "Returned Email is different from expected");
        assertEquals(storedUser.get().getUserId(), user1.getUserId(), "Returned UserId is different from expected");
        assertNotNull(storedUser.get().getId(), "Returned Id should not be null");
        assertEquals(storedUser.get().getCreatedAt(), user2.getCreatedAt(), "Returned CreatedAt date not as expected");

    }

    @DisplayName("Should return user2 details")
    @Test
    void testFindByEmail_WhenGivenEmailOfUser2_ShouldReturnAnOptionalOfUser2() {

        // Act
        Optional<User> storedUser = userRepository.findByEmail(user2.getEmail());

        // Assert
        assertTrue(storedUser.isPresent(), "User Entity should be present");
        assertEquals(storedUser.get().getUserName(), user2.getUserName(), "Returned UserName is different from expected");
        assertEquals(storedUser.get().getFirstName(), user2.getFirstName(), "Returned FirstName is different from expected");
        assertEquals(storedUser.get().getLastName(), user2.getLastName(), "Returned LastName is different from expected");
        assertEquals(storedUser.get().getEmail(), user2.getEmail(), "Returned Email is different from expected");
        assertEquals(storedUser.get().getUserId(), user2.getUserId(), "Returned UserId is different from expected");
        assertNotNull(storedUser.get().getId(), "Returned Id should not be null");
        assertEquals(storedUser.get().getCreatedAt(), user2.getCreatedAt(), "Returned CreatedAt date not as expected");
    }

    @DisplayName("Should return user by searching by UserId")
    @Test
    void testFindByUserId_WhenGivenAValidUserId_ShouldReturnOptionalUser() {

        // Arrange
        String userId = user1.getUserId();

        // Act
        Optional<User> storedUser = userRepository.findByUserId(userId);

        // Assert
        assertTrue(storedUser.isPresent(), "User should be present");
        assertEquals(storedUser.get().getUserName(), user1.getUserName(), "Username is not as expected");
    }

}
