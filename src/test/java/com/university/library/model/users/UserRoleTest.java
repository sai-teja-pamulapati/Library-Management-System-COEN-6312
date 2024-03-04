package com.university.library.model.users;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRoleTest {

    @Test
    void testEnumContainsExpectedValues() {
        assertTrue(UserRole.valueOf("ADMIN") == UserRole.ADMIN);
        assertTrue(UserRole.valueOf("STUDENT") == UserRole.STUDENT);
        assertTrue(UserRole.valueOf("STAFF") == UserRole.STAFF);
        assertTrue(UserRole.valueOf("LIBRARIAN") == UserRole.LIBRARIAN);
        assertTrue(UserRole.valueOf("FREE_USER") == UserRole.FREE_USER);
        assertTrue(UserRole.valueOf("PAID_USER") == UserRole.PAID_USER);
    }
}
