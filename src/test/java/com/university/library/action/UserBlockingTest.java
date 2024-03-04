package com.university.library.action;

import com.university.library.action.UserBlocking;
import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;
import com.university.library.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserBlockingTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        userRepository = UserRepository.getInstance();
        userRepository.clearUsers();
        User testUser = new User("Test User", "testuser@example.com", "password", "1234567890", "Test Address", "01-01-1990", "Male", UserRole.STUDENT);
        userRepository.addUser(testUser);
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        userRepository.clearUsers();
    }

    @Test
    public void testBlockUser() {
        assertFalse(userRepository.getUser("testuser@example.com").isBlocked(), "User should not be blocked initially");

        String inputData = "testuser@example.com\n";
        ByteArrayInputStream testInput = new ByteArrayInputStream(inputData.getBytes());
        System.setIn(testInput);
        UserBlocking.blockUser();

        assertTrue(userRepository.getUser("testuser@example.com").isBlocked(), "User should be blocked");
        assertFalse(outContent.toString().contains("User has been blocked"), "The output should confirm that the user has been blocked");
        System.setIn(originalIn);
    }

    @Test
    public void testUnblockUser() {
        User userToUnblock = userRepository.getUser("testuser@example.com");
        userToUnblock.blockUser();
        userRepository.updateUser(userToUnblock);

        assertTrue(userToUnblock.isBlocked(), "User should be blocked before unblocking test");

        String input = "testuser@example.com\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        UserBlocking.unblockUser();

        assertFalse(userRepository.getUser("testuser@example.com").isBlocked(), "User should be unblocked");
        assertFalse(outContent.toString().contains("User has been unblocked"), "The output should confirm that the user has been unblocked");
    }
}
