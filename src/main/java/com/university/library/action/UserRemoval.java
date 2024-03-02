package com.university.library.action;

import java.util.Scanner;
import com.university.library.repository.UserRepository;

public class UserRemoval {
    private static Scanner scanner = new Scanner(System.in);
    private static UserRepository userRepository = UserRepository.getInstance();

    public static void removeUser() {
        System.out.println("Please enter the email ID of the user you want to remove:");
        String emailId = scanner.nextLine();
        if (userRepository.removeUser(emailId)) {
            System.out.println("User removed successfully!");
        } else {
            System.out.println("User not found.");
        }
    }
}
