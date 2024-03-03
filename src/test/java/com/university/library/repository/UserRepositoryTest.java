package com.university.library.repository;

import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserRepositoryTest {

    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository = UserRepository.getInstance(); 
    }

    @Test
    public void testAddUser() {
        User user = new User("Pradheep", "pradheep.shankar@example.com", "PPSSPP123123", "1234567890", "123 Yes St", "07-09-2000", "Male", UserRole.STUDENT);
        assertTrue(userRepository.addUser(user));
    }

    @Test
    public void testRemoveUser() {
        User user = new User("Shankar", "shankar_loganathan@example.com", "PSP123123", "0987654321", "123 No St", "14-06-1971", "Male", UserRole.STAFF);
        userRepository.addUser(user);
        assertTrue(userRepository.removeUser(user.getEmailId()));
    }

    @Test
    public void testGetUser() {
        User user = new User("Mike Smith", "mike.smith@example.com", "password123", "1122334455", "789 Pine St", "03-03-1990", "Male", UserRole.ADMIN);
        userRepository.addUser(user);
        assertNotNull(userRepository.getUser(user.getEmailId()));
    }

}
