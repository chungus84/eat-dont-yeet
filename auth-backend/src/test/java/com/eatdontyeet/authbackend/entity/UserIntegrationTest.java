package com.eatdontyeet.authbackend.entity;

import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    private User user;

    @BeforeEach
    void setup() {
        user = new User("Test", "Testerson", "testtest", "test@test.com", "passtest");
    }

    @DisplayName("Create and return a user entity")
    @Test
    void testUserEntity_WhenValidUserDetailsAreProvided_ShouldReturnStoredUserDetails() {

        // Act
        User storedUser = testEntityManager.persistAndFlush(user);

        // Assert
        assertTrue(storedUser.getId() > 0, "Id should have been greater than 0");
        assertEquals(user.getUserId(), storedUser.getUserId(), "Returned UserId was not as expected");
        assertEquals(user.getFirstName(), storedUser.getFirstName(), "Returned First Name was not as expected");
        assertEquals(user.getLastName(), storedUser.getLastName(), "Returned Last Name was not as expected");
        assertEquals(user.getUserName(), storedUser.getUserName(), "Returned UserName was not as expected");
        assertEquals(user.getEmail(), storedUser.getEmail(), "Returned Email was not as expected");
        assertEquals(user.getCreatedAt(), storedUser.getCreatedAt(), "Returned a CreatedAt date that was not as expected");

    }

    @DisplayName("Should throw PersistenceException when firstName is too long")
    @Test
    void testUserEntity_WhenGivenAFirstNameThatIsTooLong_ShouldThrowPersistenceException() {

        // Arrange
        user.setFirstName("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        // Act
        assertThrows(PersistenceException.class, ()-> {
            testEntityManager.persistAndFlush(user);
        }, "Should have thrown PersistenceException");

    }

    @DisplayName("Should throw PersistenceException when lastName is too long")
    @Test
    void testUserEntity_WhenGivenALastNameThatIsTooLong_ShouldThrowPersistenceException() {

        // Arrange
        user.setLastName("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        // Act & Assert
        assertThrows(PersistenceException.class, ()-> {
            testEntityManager.persistAndFlush(user);
        }, "Should have thrown PersistenceException");
    }

    @DisplayName("Should throw PersistenceException when userName is too long")
    @Test
    void testUserEntity_WhenGivenAUserNameThatIsTooLong_ShouldThrowPersistenceException() {

        // Arrange
        user.setUserName("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        // Act & Assert
        assertThrows(PersistenceException.class, ()-> {
            testEntityManager.persistAndFlush(user);
        }, "Should have thrown PersistenceException");
    }


}
