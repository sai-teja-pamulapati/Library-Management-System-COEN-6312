package com.university.library.action;

import com.university.library.model.users.User;
import com.university.library.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserLoginTest {

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = UserRepository.getInstance();
        userRepository.clearUsers();

        // Adding users for testing.
        userRepository.addUser(new User(null, "John Smith", "john.smith@gmail.com", "john123", "0987654321", "456 Elm St", "1992-02-02", "Male"));
        userRepository.addUser(new User(null, "Admin User", "admin@gmail.com", "admin123", "1234567890", "123 Admin St", "1990-01-01", "Male"));
    }

    @Test
    public void testLogin() {
        User user = User.login("admin@gmail.com", "admin123");
        assertNotNull(user, "Admin should successfully log in with correct credentials.");

        User user1 = User.login("john.smith@gmail.com", "john123");
        assertNotNull(user1, "John Smith should successfully log in with correct credentials.");
    }

    @Test
    public void testWrongEmail() {
        User user = User.login("admni@gmail.com", "admin123");
        assertNull(user, "Login with a non-existent email should fail.");

        User user1 = User.login("john.smmoth@gmail.com", "john123");
        assertNull(user1, "Login with another non-existent email should fail.");
    }

    @Test
    public void testWrongPassword() {
        User user = User.login("admin@gmail.com", "Admin123");
        assertNull(user, "Login with a wrong password for admin should fail.");

        User user1 = User.login("john.smith@gmail.com", "john1234");
        assertNull(user1, "Login with a wrong password for John Smith should fail.");
    }
}
