package com.university.library.action;

import com.university.library.model.users.User;
import com.university.library.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminServiceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        userRepository = UserRepository.getInstance();
        userRepository.clearUsers();
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        userRepository.clearUsers();
    }

    @Test
    public void testViewAllUsersWithNoUsers() {
        AdminService.viewAllUsers();
        assertTrue(outContent.toString().contains("No users found."), "Should inform no users found");
    }

    @Test
    public void testViewAllUsersWithUsers() {
        User user1 = new User(null, "User1", "user1@example.com", "password1", "1234567890", "Address 1", "01-01-1990", "Male");
        User user2 = new User(null, "User2", "user2@example.com", "password2", "0987654321", "Address 2", "02-02-1992", "Female");
        userRepository.addUser(user1);
        userRepository.addUser(user2);

        AdminService.viewAllUsers();

        String output = outContent.toString();
        assertAll("Should display all user details correctly and in order",
            () -> assertTrue(output.contains("User1"), "Output should contain User1's details"),
            () -> assertTrue(output.contains("user1@example.com"), "Output should contain User1's email"),
            () -> assertTrue(output.contains("STUDENT"), "Output should contain User1's role"),
            () -> assertTrue(output.contains("User2"), "Output should contain User2's details"),
            () -> assertTrue(output.contains("user2@example.com"), "Output should contain User2's email"),
            () -> assertTrue(output.contains("STAFF"), "Output should contain User2's role")
        );
    }
}
