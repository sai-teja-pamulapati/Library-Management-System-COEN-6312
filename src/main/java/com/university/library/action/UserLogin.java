package com.university.library.action;

import java.io.Console;
import java.util.Scanner;
import com.university.library.model.users.User; 
import com.university.library.repository.UserRepository;

public class UserLogin {

    private static Scanner scanner = new Scanner(System.in);
    private static UserRepository userRepository = UserRepository.getInstance();

    public static void login() {
        Console console = System.console();
        if (console == null) {
            System.out.println("No console available");
            return;
        }
        System.out.println("Please enter your email Id:");
        String emailId = scanner.nextLine();
        System.out.println("Please enter your password:");
        String password = new String(console.readPassword());
        
        User user = userRepository.getUser(emailId);
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("Invalid Credentials!!");
            return;
        }
        System.out.println("Login Successful!");

        switch (user.getUserRole()) {
            case ADMIN:
                processAdminUser();
                break;
            case STUDENT:
                processStudentUser();
                break;
            case STAFF:
                processStaffUser();
                break;
            case LIBRARIAN:
                processLibrarianUser();
                break;
            case FREE_USER:
                processFreeUser();
                break;
            case PAID_USER:
                processPaidUser();
                break;
            default:
                System.out.println("Invalid user role.");
                break;
        }
    }

    private static void processFreeUser() {

    }

    private static void processPaidUser() {

    }

    private static void processLibrarianUser() {
        while(true){
            try{
                System.out.println("Choose from the following options\n" +
                "1: Browse Catalouge\n" +
                "2: See browsing history\n" +
                "3: View Newsletter\n" +
                "4: View Notifications\n" +
                "5: Add Book\n" +
                "6: Remove Book\n" +
                "7: Update Book Details\n" +
                "8: View Library Activities\n" +
                "9: Update NewsLetter\n" +
                "10: Logout\n");
                String Librariancommands = scanner.nextLine();
                switch(Librariancommands){
                    case "1":
                        //Left
                    case "2":
                        //left
                    case "3":
                        //left
                    case "4":
                        //left
                    case "5":
                        //left
                    case "6":
                        //left
                    case "7":
                        //left
                    case "8":
                        //left
                    case "9":
                        //left
                    case "10":
                        //left
                    default:
                        throw new IllegalArgumentException("Invalid Option!");
                }
            }catch(Exception e){
                System.out.println(e.getLocalizedMessage());
            }
        }

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
        while (true) {
			try {
                System.out.print("Choose from the following options\n" +
                    "1. Add User\n" +
                    "2. Remove User\n" +
                    "3. Block User\n" +
                    "4. Update Newsletter\n" +
                    "5. View User Activities\n" +
                    "6. Logout\n");
                String studentCommands = scanner.nextLine();
                switch (studentCommands) {
                    case "1":
                        // TODO
                        break;
                    case "2":
                        // TODO
                        break;
                    case "3":
                        // TODO
                        break;
                    case "4":
                        // TODO
                        break;
                    case "5":
                        // TODO
                        break;
                    case "6":
                        return;
                    default:
                        throw new IllegalArgumentException("Invalid option!");
                }
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
    }

}
