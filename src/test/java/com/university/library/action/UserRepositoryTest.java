package com.university.library.action;

import com.university.library.model.users.User;
import com.university.library.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = UserRepository.getInstance();
    }

    @Test
public void testAddUser() {
    int initialSize = userRepository.getAllUsers().size();
    User user = new User(null, "Pradheep", "pradheep.shankar@example.com", "PPSSPP123123", "1234567890", "123 Yes St", "07-09-2000", "Male");
    assertTrue(userRepository.addUser(user));
    assertEquals(initialSize + 1, userRepository.getAllUsers().size(), "Repository size should increase by 1");
    User retrievedUser = userRepository.getUser("pradheep.shankar@example.com");
    assertEquals("Pradheep", retrievedUser.getName(), "Added user's name should match");
}

@Test
public void testRemoveUser() {
    User user = new User(null, "Shankar", "shankar_loganathan@example.com", "PSP123123", "0987654321", "123 No St", "14-06-1971", "Male");
    userRepository.addUser(user);
    int sizeBeforeRemoval = userRepository.getAllUsers().size();
    assertTrue(userRepository.removeUser(user.getEmailId()));
    assertEquals(sizeBeforeRemoval - 1, userRepository.getAllUsers().size(), "Repository size should decrease by 1");
    assertNull(userRepository.getUser(user.getEmailId()), "Removed user should not be retrievable");
}

@Test
public void testGetUser() {
    User user = new User(null, "Mike Smith", "mike.smith@example.com", "password123", "1122334455", "789 Pine St", "03-03-1990", "Male");
    userRepository.addUser(user);
    User retrievedUser = userRepository.getUser("mike.smith@example.com");
    assertNotNull(retrievedUser, "Retrieved user should not be null");
    assertEquals("Mike Smith", retrievedUser.getName(), "Retrieved user's name should match");
}

}
