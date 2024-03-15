package com.university.library.action;

import com.university.library.model.users.User;
import com.university.library.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private UserRepository userRepository;
    private User testUser;

    @Before
    public void setUp() {
        userRepository = UserRepository.getInstance();
        userRepository.clearUsers();
        testUser = new User(null, "Test User", "testuser@example.com", "password", "1234567890", "Test Address", "01-01-1990", "Male");
    }

    @Test
    public void testUserRegistrationSuccess() {
        User registeredUser = User.register(testUser);
        assertNotNull("User should be registered successfully", registeredUser);
    }

    @Test
    public void testUserRegistrationFailure() {
        userRepository.addUser(testUser);
        User duplicateUser = new User(null, "Test User", "testuser@example.com", "password", "1234567890", "Test Address", "01-01-1990", "Male");
        User registeredUser = User.register(duplicateUser);
        assertNull("Duplicate user registration should fail", registeredUser);
    }

    @Test
    public void testUserLoginSuccess() {
        userRepository.addUser(testUser);
        User loggedInUser = User.login("testuser@example.com", "password");
        assertNotNull("Valid login should succeed", loggedInUser);
    }

    @Test
    public void testUserLoginFailure() {
        User loggedInUser = User.login("nonexistentuser@example.com", "wrongpassword");
        assertNull("Invalid login should fail", loggedInUser);
    }

    @Test
    public void testBlockUnblockUser() {
        userRepository.addUser(testUser);
        assertFalse("User should not be blocked initially", testUser.isBlocked());

        testUser.blockUser();
        assertTrue("User should be blocked", testUser.isBlocked());

        testUser.unBlockUser();
        assertFalse("User should be unblocked", testUser.isBlocked());
    }

    @Test
    public void testUserUpdate() {
        userRepository.addUser(testUser);
        testUser.setAddress("Updated Address");
        testUser.setMobileNumber("9876543210");
        userRepository.updateUser(testUser);

        User updatedUser = userRepository.getUser("testuser@example.com");
        assertEquals("Address should be updated", "Updated Address", updatedUser.getAddress());
        assertEquals("Mobile number should be updated", "9876543210", updatedUser.getMobileNumber());
    }

}
