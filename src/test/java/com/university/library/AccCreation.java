package com.university.library;


import java.util.Scanner;

public class AccCreation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserAccountManager accountManager = new UserAccountManager();

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Choose user role (STUDENT, STAFF, FREE_USER, PAID_USER):");
        String roleInput = scanner.nextLine();
        User.UserRole role;
        try {
            role = User.UserRole.valueOf(roleInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role. Exiting account creation.");
            scanner.close();
            return;
        }

        boolean isCreated = false;
        while (!isCreated) {
            System.out.println("Enter password (password must be at least 8 characters long):");
            String password = scanner.nextLine();

            isCreated = accountManager.createUserAccount(username, password, role);
            if (!isCreated) {
                System.out.println("Password did not meet the criteria. Please try again.");
            }
        }

        System.out.println("Account creation successful.");
        scanner.close();
    }
}
