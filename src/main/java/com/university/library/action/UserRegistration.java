package com.university.library.action;

import java.util.Scanner;

import com.university.library.model.User;
import com.university.library.model.UserRole;
import com.university.library.repository.UserRepository;

public class UserRegistration {

    private static Scanner scanner = new Scanner(System.in);
    private static UserRepository userRepository = UserRepository.getInstance();

    public static void register() {
        System.out.println("Please enter your name:");
        String name = scanner.nextLine();
        
        System.out.println("Please enter your email ID:");
        String emailId = scanner.nextLine();
        
        System.out.println("Please enter your password:");
        String password = scanner.nextLine();
        
        System.out.println("Please enter your mobile number:");
        String mobileNumber = scanner.nextLine();
        
        System.out.println("Please enter your address:");
        String address = scanner.nextLine();
        
        System.out.println("Please enter your date of birth (YYYY-MM-DD):");
        String dateOfBirth = scanner.nextLine();
        
        System.out.println("Please enter your gender (Male/Female/Other):");
        String gender = scanner.nextLine();
        
        System.out.println("Please select your role (ADMIN, STUDENT, STAFF, LIBRARIAN, FREE_USER, PAID_USER):");
        UserRole userRole = UserRole.valueOf(scanner.nextLine().toUpperCase());

        User user = new User();
        user.setName(name);
        user.setEmailId(emailId);
        user.setPassword(password);
        user.setMobileNumber(mobileNumber);
        user.setAddress(address);
        user.setDateOfBirth(dateOfBirth);
        user.setGender(gender);
        user.setUserRole(userRole);

        if (userRepository.addUser(user)) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Registration failed. User with this email already exists.");
        }
    }
}
