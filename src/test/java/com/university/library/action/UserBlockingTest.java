package com.university.library.action;

import com.university.library.action.UserBlocking;
import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;
import com.university.library.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class UserBlockingTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    private UserRepository userRepository;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        userRepository = UserRepository.getInstance();
        userRepository.clearUsers();
        User testUser = new User("Test User", "testuser@example.com", "password", "1234567890", "Test Address", "01-01-1990", "Male", UserRole.STUDENT);
        userRepository.addUser(testUser);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        userRepository.clearUsers();
    }

    @Test
public void testBlockUser() {
    String inputData = "testuser@example.com\n";
    ByteArrayInputStream testInput = new ByteArrayInputStream(inputData.getBytes());
    System.setIn(testInput);
    UserBlocking.blockUser();
    assertTrue("User should be blocked", userRepository.getUser("testuser@example.com").isBlocked());
    System.setIn(originalIn);
}

    @Test
    public void testUnblockUser() {
        User userToUnblock = userRepository.getUser("testuser@example.com");
        userToUnblock.blockUser();
        userRepository.updateUser(userToUnblock);

        String input = "testuser@example.com\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        UserBlocking.unblockUser();

        assertFalse("User should be unblocked", userRepository.getUser("testuser@example.com").isBlocked());
    }

}
