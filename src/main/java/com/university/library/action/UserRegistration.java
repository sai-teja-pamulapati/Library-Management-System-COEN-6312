package com.university.library.action;

import java.io.Console;
import java.util.Scanner;

import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;
import com.university.library.repository.UserRepository;

public class UserRegistration {

    private static Scanner scanner = new Scanner(System.in);
    private static UserRepository userRepository = UserRepository.getInstance();

    public static void register() {

        Console console = System.console();

        System.out.println("Please enter your name:");
        String name = scanner.nextLine();
        
        System.out.println("Please enter your email ID:");
        String emailId = scanner.nextLine();
        
        System.out.println("Please enter your password:");
        String password = new String(console.readPassword());
        
        System.out.println("Please enter your mobile number:");
        String mobileNumber = scanner.nextLine();
        
        System.out.println("Please enter your address:");
        String address = scanner.nextLine();
        
        System.out.println("Please enter your date of birth (YYYY-MM-DD):");
        String dateOfBirth = scanner.nextLine();
        
        String gender = "";
        while(true) {
            System.out.print("Please select your gender:\n" +
                "1. Male\n" +
                "2. Female\n" +
                "3. Other\n");
            String genderOption = scanner.nextLine();
            switch (genderOption) {
                case "1":
                    gender = "Male";                        
                    break;
                case "2":
                    gender = "Female";
                    break;
                case "3":
                    gender = "Other";
                    break;
                default:
                    System.out.println("Invalid option!");
                    continue;
                }
            break;
        }

        UserRole userRole = null;
        while (true) {
            System.out.println("Please select your role:");
            UserRole[] roles = UserRole.values();
            for (int i = 0; i < roles.length; i++) {
                System.out.println((i + 1) + ". " + roles[i].name());
            }
            try {
                int roleOption = Integer.parseInt(scanner.nextLine()) - 1;
                if (roleOption >= 0 && roleOption < roles.length) {
                    userRole = roles[roleOption];
                } else {
                    System.out.println("Invalid selection. Please try again.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            break;
        }

        User.register(name, emailId, password, mobileNumber, address, dateOfBirth, gender, userRole, userRepository);

    }
}
