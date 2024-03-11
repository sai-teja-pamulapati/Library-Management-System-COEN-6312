package com.university.library.model.users.academic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcademicUserTest {

    @Test
    public void testIssueDateValid() {
        //future issue date
        Admin adminFutureDate = new Admin("userId", "John Doe", "john@example.com", "password",
                "1234567890", "123 Main St", "1990-01-01", "Male", "university123", "2026-01-01",
                "Office Location", "Office Hours", "Contract Type");

        //past issue date
        Admin adminPastDate = new Admin("userId", "John Doe", "john@example.com", "password",
                "1234567890", "123 Main St", "1990-01-01", "Male", "university123", "2020-01-01",
                "Office Location", "Office Hours", "Contract Type");

        // Testing future issue date
        assertFalse(adminFutureDate.isIssueDateValid(), "Issue date should not be valid");

        // Testing past issue date
        assertTrue(adminPastDate.isIssueDateValid(), "Issue date should be valid");
    }

    @Test
    public void testIssueDateNotValid() {
        // future issue date
        AcademicUser academicUserFutureDate = new Admin("userId", "John Doe", "john@example.com", "password",
                "1234567890", "123 Main St", "1990-01-01", "Male", "university123", "2026-01-01",
                "Office Location", "Office Hours", "Contract Type");

        //  past issue date
        AcademicUser academicUserPastDate = new Admin("userId", "John Doe", "john@example.com", "password",
                "1234567890", "123 Main St", "1990-01-01", "Male", "university123", "2020-01-01",
                "Office Location", "Office Hours", "Contract Type");

        // future issue date
        assertFalse(academicUserFutureDate.isIssueDateValid(), "Issue date should not be valid");

        // past issue date
        assertTrue(academicUserPastDate.isIssueDateValid(), "Issue date should be valid");
    }
    }