package com.university.library;
import java.util.Scanner;
import com.university.library.action.UserLogin;
import com.university.library.action.UserRegistration;

public class App {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                initializeSystem();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void initializeSystem() {
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
