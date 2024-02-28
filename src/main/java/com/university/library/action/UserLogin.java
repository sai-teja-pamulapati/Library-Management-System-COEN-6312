package com.university.library.action;

import java.io.Console;
import java.util.Scanner;

import com.university.library.App;
import com.university.library.model.users.User;

public class UserLogin {

    private static Scanner scanner = new Scanner(System.in);
    private static AssetManagement assetManagement = AssetManagement.getInstance();

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
        // String password = scanner.nextLine();

        User user = User.login(emailId, password);
        if (user == null) {
            return;
        }

        App.setLoggedInUser(user);
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
        App.setLoggedInUser(null);
    }

    private static void processFreeUser() {
        while (true) {
            try {
                System.out.println("Choose from the following options\n" +
                        "1: Browse Catalouge\n" +
                        "2: Buy membership\n" +
                        "6: Logout\n");
                String paidUserCommands = scanner.nextLine();
                switch (paidUserCommands) {
                    case "1":
                        assetManagement.browse();
                    case "2":
                        // buy membership coming soon

                    case "3":
                        return; // logout
                    default:
                        throw new IllegalArgumentException("Invalid Option!");
                }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

    }

    private static void processPaidUser() {
        while (true) {
            try {
                System.out.println("Choose from the following options\n" +
                        "1: Browse Catalouge\n" +
                        "2: See browsing history\n" +
                        "3: View Newsletter\n" +
                        "4: View Notifications\n" +
                        "5: Pay Fines\n" +
                        "6: Logout\n");
                String paidUserCommands = scanner.nextLine();
                switch (paidUserCommands) {
                    case "1":
                        assetManagement.browse();
                    case "2":
                        assetManagement.getBorrowingHistory();
                    case "3":
                        // views newsletter
                    case "4":
                        // View Notifications
                    case "5":
                        // View Notifications
                    case "6":
                        // cancel membership coming soon
                    case "7":
                        // renew membership coming soon
                    case "8":
                        return;// logout
                    default:
                        throw new IllegalArgumentException("Invalid Option!");
                }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

    }

    private static void processLibrarianUser() {
        while (true) {
            try {
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
                String librarianCommands = scanner.nextLine();
                switch (librarianCommands) {
                    case "1":
                        // Left
                    case "2":
                        // left
                    case "3":
                        // left
                    case "4":
                        // left
                    case "5":
                        // left
                    case "6":
                        // left
                    case "7":
                        // left
                    case "8":
                        // left
                    case "9":
                        // left
                    case "10":
                        // left
                    default:
                        throw new IllegalArgumentException("Invalid Option!");
                }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

    }

    private static void processStaffUser() {

    }

    private static void processStudentUser() {
        while (true) {
            try {
                System.out.print("Choose from the following options\n" +
                        "1. Browse Catalogue\n" +
                        "2. View borrowing history\n" +
                        "3. View Newsletter\n" +
                        "4. View Notifications\n" +
                        "5. Book/Cancel Discussion Room\n" +
                        "6. Pay Fines\n" +
                        "7. Logout\n");
                String studentCommands = scanner.nextLine();
                switch (studentCommands) {

                    case "1":
                        assetManagement.browse();
                        break;
                    case "2":
                        assetManagement.getBorrowingHistory();
                        break;
                    case "3":
                        // Todo
                        break;
                    case "4":
                        // Todo
                        break;
                    case "5":
                        // TODO
                        break;
                    case "6":
                        // Todo
                        break;
                    case "7":
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
                        "4. Unblock User\n" +
                        "5. Update Newsletter\n" +
                        "6. View User Activities\n" +
                        "7. Logout\n");
                String studentCommands = scanner.nextLine();
                switch (studentCommands) {
                    case "1":
                        User newUser = new User();
                        UserRegistration.register(false);
                        break;
                    case "2":
                        UserRemoval.removeUser();
                        break;
                    case "3":
                        UserBlocking.blockUser();
                        break;
                    case "4":
                        UserBlocking.unblockUser();
                        break;
                    case "5":
                        // TODO
                        break;
                    case "6":
                        // TODO
                        break;
                    case "7":
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
