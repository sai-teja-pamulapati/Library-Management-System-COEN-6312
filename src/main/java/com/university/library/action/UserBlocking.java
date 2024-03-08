package com.university.library.action;

import java.util.Scanner;

import com.university.library.model.users.User;
import com.university.library.model.users.academic.Admin;
import com.university.library.repository.UserRepository;

public class UserBlocking {
    private static Scanner scanner = new Scanner(System.in);
    private static UserRepository userRepository = UserRepository.getInstance();

    public static void blockUser(User loggedInUser) {
        if (!(loggedInUser instanceof Admin)) {
            System.out.println("You do not have permission to block users.");
            return;
        }
        System.out.println("Enter the email ID of the user you want to block:");
        String emailId = scanner.nextLine();
        User userToBlock = userRepository.getUser(emailId);
        if (userToBlock != null) {
            if (userToBlock.isBlocked()) {
                System.out.println("User is already blocked.");
            } else {
                userToBlock.blockUser();
                System.out.println("User blocked successfully!");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public static void unblockUser(User loggedInUser) {
        if (!(loggedInUser instanceof Admin)) {
            System.out.println("You do not have permission to unblock users.");
            return;
        }
        System.out.println("Enter the email ID of the user you want to unblock:");
        String emailId = scanner.nextLine();
        User userToUnblock = userRepository.getUser(emailId);
        if (userToUnblock != null) {
            if (!userToUnblock.isBlocked()) {
                System.out.println("User is already unblocked.");
            } else {
                userToUnblock.unBlockUser();
                System.out.println("User unblocked successfully!");
            }
        } else {
            System.out.println("User not found.");
        }
    }

}