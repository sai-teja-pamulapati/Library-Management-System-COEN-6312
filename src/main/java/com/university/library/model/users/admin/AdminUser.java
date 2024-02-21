package com.university.library.model.users.admin;

import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;
import com.university.library.repository.UserRepository;

public class AdminUser extends User{

    public AdminUser(String name, String emailId, String password, String phoneNumber, String address, String dateOfBirth, String gender) {
        super(name, emailId, password, phoneNumber, address, dateOfBirth, gender, UserRole.ADMIN);
    }

        public boolean addUser(String name, String email, String password, String phoneNumber, String address, String dateOfBirth, String gender, UserRole role) {
            // Get the instance of UserRepository
            UserRepository userRepository = UserRepository.getInstance();

            // Check if the user already exists
            if (userRepository.exists(email)) {
                System.out.println("User with email " + email + " already exists.");
                return false;
            }

            // Create a new user object
            User newUser = new User(name, email, password, phoneNumber, address, dateOfBirth, gender, role);

            // Add the user using UserRepository
            boolean success = userRepository.addUser(newUser);
            if (success) {
                System.out.println("User added successfully.");
            } else {
                System.out.println("Failed to add user.");
            }
            return success;
        }

}

