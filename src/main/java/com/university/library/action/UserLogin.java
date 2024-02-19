package com.university.library.action;

import com.university.library.model.User;

import java.io.Console;
import java.util.Scanner;

public class UserLogin {

    private static Scanner scanner = new Scanner(System.in);

    public static void login() {
        Console console = System.console();
        System.out.println("Please enter your email Id");
        String emailId = scanner.nextLine();
        System.out.println("Please enter your password");
        String password = new String(console.readPassword());
        User user = User.login(emailId, password);
        if (user == null){
            System.out.println("Invalid Credentials!!");
        }
        switch (user.getUserRole()) {
            case ADMIN:
                processAdminUser();
                break;
            case STUDENT :
                processStudentUser();
                break;
            case STAFF :
                processStaffUser();
                break;
            case LIBRARIAN :
                processLibrarianUser();
                break;
            case FREE_USER :
                processFreeUser();
                break;
            case PAID_USER :
                processPaidUser();
                break;
        }
    }

    private static void processFreeUser() {

    }

    private static void processPaidUser() {

    }

    private static void processLibrarianUser() {

    }

    private static void processStaffUser() {

    }

    private static void processStudentUser () {
         while (true) {
			try {
                System.out.print("Choose from the following options\n" +
                    "1. Browse Catalogue\n" +
                    "2. See borrowing history\n" +
                    "3. View Newsletter\n" + 
                    "4. Logout\n");
                String studentCommands = scanner.nextLine();
                switch (studentCommands) {
                    case "1":
                        // TODO
                        break;
                    case "2":
                        // TODO 
                        break;
                    case "3":
                        // Todo
                        break;
                    case "4": 
                        return;     
                    default:
                        throw new IllegalArgumentException("Invalid option!");
                }
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}

    }

    private static void processAdminUser() {
    }

    public static void register() {

    }
}
