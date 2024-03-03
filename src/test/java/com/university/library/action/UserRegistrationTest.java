package com.university.library.action;

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

class UserRegistrationTest {

    @Mock
    private Scanner mockScanner;

    @Mock
    private Console mockConsole;

    @Mock
    private UserRepository mockUserRepository;

    private UserRegistration userRegistration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userRegistration = new UserRegistration(mockScanner, mockConsole, mockUserRepository);
    }

    @Test
    void registerUserSuccessfully() {
        when(mockScanner.nextLine()).thenReturn("John Doe", "john.doe@example.com", "1234567890", "123 Main St", "2000-01-01", "1", "2");
        when(mockConsole.readPassword()).thenReturn("password".toCharArray());
        when(mockUserRepository.addUser(any(User.class))).thenReturn(true);

        userRegistration.register(true);

        verify(mockUserRepository, times(1)).addUser(any(User.class));
    }

    @Test
    void registrationFailsDueToExistingEmail() {
        when(mockScanner.nextLine()).thenReturn("Jane Doe", "jane.doe@example.com", "9876543210", "456 Main St", "1990-02-02", "2", "1");
        when(mockConsole.readPassword()).thenReturn("securepassword".toCharArray());
        when(mockUserRepository.addUser(any(User.class))).thenReturn(false);

        userRegistration.register(true);

        verify(mockUserRepository, times(1)).addUser(any(User.class));
        // Additional logic to verify the output to the user about the failure
    }

    // Additional tests can be added here for invalid inputs, etc.
}
