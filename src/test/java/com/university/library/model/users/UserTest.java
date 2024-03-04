package com.university.library.model.users;

import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;
import com.university.library.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private UserRepository userRepository;
    private User testUser;

    @BeforeEach
    public void setUp() {
        userRepository = UserRepository.getInstance();
        userRepository.clearUsers();
        testUser = new User("Test User", "testuser@example.com", "password", "1234567890", "Test Address", "01-01-1990", "Male", UserRole.STAFF);
    }

    @Test
    public void testUserRegistrationSuccess() {
    User registeredUser = User.register(testUser);
    assertNotNull(registeredUser, "User should be registered successfully");

    assertEquals("Test User", registeredUser.getName(), "Name should match the registered user");
    assertEquals("testuser@example.com", registeredUser.getEmailId(), "Email should match the registered user");
    assertEquals("1234567890", registeredUser.getMobileNumber(), "Mobile number should match the registered user");
    assertEquals("Test Address", registeredUser.getAddress(), "Address should match the registered user");
    assertEquals("01-01-1990", registeredUser.getDateOfBirth(), "Date of birth should match the registered user");
    User retrievedUser = userRepository.getUser("testuser@example.com");
    assertNotNull(retrievedUser, "Registered user should be retrievable from the repository");
    assertEquals("Test User", retrievedUser.getName(), "Retrieved user's name should match");
    }


    @Test
    public void testUserRegistrationFailure() {
        userRepository.addUser(testUser);
        User duplicateUser = new User("Test User", "testuser@example.com", "password", "1234567890", "Test Address", "01-01-1990", "Male", UserRole.STUDENT);
        User registeredUser = User.register(duplicateUser);
        assertNull(registeredUser, "Duplicate user registration should fail");
    }

    @Test
    public void testUserLoginSuccess() {
        userRepository.addUser(testUser);
        User loggedInUser = User.login("testuser@example.com", "password");
        assertNotNull(loggedInUser, "Valid login should succeed");
        assertEquals("Test User", loggedInUser.getName(), "Name should match the logged in user");
    }

    @Test
    public void testUserLoginFailure() {
        User loggedInUser = User.login("nonexistentuser@example.com", "wrongpassword");
        assertNull(loggedInUser, "Invalid login should fail");
    }

    @Test
    public void testBlockUnblockUser() {
        userRepository.addUser(testUser);
        assertFalse(testUser.isBlocked(), "User should not be blocked initially");

        testUser.blockUser();
        assertTrue(testUser.isBlocked(), "User should be blocked");

        testUser.unBlockUser();
        assertFalse(testUser.isBlocked(), "User should be unblocked");
    }

    @Test
    public void testUserUpdate() {
    userRepository.addUser(testUser);
    testUser.setAddress("Updated Address");
    testUser.setMobileNumber("9876543210");
    userRepository.updateUser(testUser);

    User updatedUser = userRepository.getUser("testuser@example.com");
    assertEquals("Updated Address", updatedUser.getAddress(), "Address should be updated");
    assertEquals("9876543210", updatedUser.getMobileNumber(), "Mobile number should be updated");
    assertEquals("Test User", updatedUser.getName(), "Name should remain unchanged");

    }

}