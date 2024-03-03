package com.university.library.model.users;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.university.library.App;
import com.university.library.model.LoanAsset;
import com.university.library.model.assets.Asset;
import com.university.library.model.assets.physical.Book;
import com.university.library.model.assets.physical.Laptop;
import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;
import com.university.library.repository.AssetRepository;
import com.university.library.repository.LoanAssetRepository;
import com.university.library.repository.UserRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import java.io.Console;
import java.util.Scanner;

class UserTest {

    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        mockUserRepository = mock(UserRepository.class);
        User.setUserRepository(mockUserRepository); // Assuming setUserRepository is a method added for testing to inject the mock
    }

    @Test
    void testAddUser() {
        User newUser = new User("John Doe", "john@example.com", "password", "1234567890", "Test Address", "1990-01-01", "Male", UserRole.STUDENT);
        when(mockUserRepository.addUser(any(User.class))).thenReturn(true);
        User registeredUser = newUser.addUser();
        assertNotNull(registeredUser);
        verify(mockUserRepository, times(1)).addUser(any(User.class));
    }

    @Test
    void testBlockUnblockUser() {
        User user = new User("John Doe", "john@example.com", "password", "1234567890", "Test Address", "1990-01-01", "Male", UserRole.STUDENT);
        user.blockUser();
        assertTrue(user.isBlocked());
        user.unBlockUser();
        assertFalse(user.isBlocked());
    }

    // Additional tests like login can be similar to addUser, focusing on mocking UserRepository responses
}
