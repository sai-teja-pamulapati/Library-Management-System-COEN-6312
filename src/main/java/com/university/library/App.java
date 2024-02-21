package com.university.library;
import java.util.Scanner;
import com.university.library.action.UserLogin;
import com.university.library.action.UserRegistration;
import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;
import com.university.library.repository.UserRepository;

public class App {

    private static Scanner scanner = new Scanner(System.in);
    private static UserRepository userRepository = UserRepository.getInstance();

    public static void main(String[] args) {
        while (true) {
            try {
                initializeSystem();
                executeWorkFlow();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private static void initializeSystem() {
        // Hardcoded sample users
        userRepository.addUser(new User("Admin", "admin@gmail.com", "admin123", "1234567890", "123 Main St", "1990-01-01", "Male", UserRole.ADMIN));
        userRepository.addUser(new User("John Smith", "john.smith@gmail.com", "john123", "0987654321", "456 Elm St", "1992-02-02", "Female", UserRole.STAFF));
        userRepository.addUser(new User("Sam Wilson", "sam.wilson@gmail.com", "sam123", "1122334455", "789 Pine St", "1993-03-03", "Other", UserRole.LIBRARIAN));

        // Hardcoded Assets

    }

    private static void executeWorkFlow() {
        System.out.print("Welcome to Library Management System \n" +
                "Choose from the following options. \n" +
                "1. User Login \n" +
                "2. User Registration \n" +
                "3. Exit \n");
        String firstCommand = scanner.nextLine();
        switch (firstCommand) {
            case "1":
                UserLogin.login();
                break;
            case "2":
                UserRegistration.register();
                break;
            case "3":
                System.out.println("Bye");
                System.exit(0);
            default:
                throw new IllegalArgumentException("Invalid option!");
        }
    }
}
