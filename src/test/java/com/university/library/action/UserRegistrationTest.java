package com.university.library.action;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRegistrationTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream testInput;
    private final PrintStream originalIn = System.out;

    @BeforeEach
    public void setUpStreams() {
        // Setup done in each test to allow different inputs
    }

    private void setInput(String data) {
        testInput = new ByteArrayInputStream(data.getBytes());
        System.setOut(new PrintStream(outContent));
        System.setIn(testInput);
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(System.in);
    }

    @Test
    public void testRegistrationWithMissingInput() {
        setInput("John Doe\njohn.doe@example.com\n\n1234567890\n123 Main St\n2000-01-01\n1\n2");
        // UserRegistration.register(true);
        assertTrue(outContent.toString().contains("Invalid input") || outContent.toString().contains("failed"));
    }

    @Test
    public void testRegistrationWithInvalidEmail() {
        setInput("John Doe\nnotanemail\npassword\n1234567890\n123 Main St\n2000-01-01\n1\n2");
        // UserRegistration.register(true);
        assertTrue(outContent.toString().contains("Invalid input") || outContent.toString().contains("failed"));
    }

    @Test
    public void testRegistrationWithInvalidGenderSelection() {
        setInput("John Doe\njohn.doe@example.com\npassword\n1234567890\n123 Main St\n2000-01-01\n4\n2");
        // UserRegistration.register(true);
        assertTrue(outContent.toString().contains("Invalid option"));
    }

    @Test
    public void testRegistrationWithInvalidRoleSelection() {
        setInput("John Doe\njohn.doe@example.com\npassword\n1234567890\n123 Main St\n2000-01-01\n1\n99");
        // UserRegistration.register(true);
        assertTrue(outContent.toString().contains("Invalid selection"));
    }

    // Note: Uncomment UserRegistration.register(true); in each test to run the registration process
    // after adapting UserRegistration for testability or choosing an integration testing approach.
}
