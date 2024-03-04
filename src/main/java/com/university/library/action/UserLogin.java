package com.university.library.action;

import java.io.Console;
import java.util.Scanner;
import com.university.library.App;
import com.university.library.model.users.User;

public class UserLogin {

    private static Scanner scanner = new Scanner(System.in);
    private static AssetManagement assetManagement = AssetManagement.getInstance();
    private static PresentationRoomManagement roomManagement = new PresentationRoomManagement();

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
                        "1. Browse Catalogue\n" +
                        "2. View borrowing history\n" +
                        "3: View Newsletter\n" +
                        "4: buy membership\n" +
                        "5: Logout\n"+
                        "******************************************************************************************\n");
                String freeUserCommands = scanner.nextLine();
                switch (freeUserCommands) {
                    case "1":
                        assetManagement.browse();
                        break;
                    case "2":
                        assetManagement.getBorrowingHistory(); //
                        break;
                    case "3":
                        ViewNews.viewNewsletters();
                        break;
                    case "4":
                        MembershipManager.buyMembership();
                        break;
                    case "5":
                        return;

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
                        "1. Browse Catalogue\n" +
                        "2. View borrowing history\n" +
                        "3: View Newsletter\n" +
                        "4: View Notifications\n" +
                        "5: Pay Fines\n" +
                        "6: display membership\n" +
                        "7: cancel membership\n" +
                        "8 : renew membership\n" +
                        "9: Logout\n"+
                        "******************************************************************************************\n");

                String paidUserCommands = scanner.nextLine();
                switch (paidUserCommands) {
                    case "1":
                        assetManagement.browse();
                        break;
                    case "2":
                        assetManagement.getBorrowingHistory();
                        break;
                    case "3":
                        ViewNews.viewNewsletters();

                    case "4":
                        // View Notifications
                    case "5":
                        // pay fine
                    case "6":
                        MembershipManager.displayMembership(App.getLoggedInUser().getUserId());
                        break;
                    case "7":
                        MembershipManager.cancelMembership(App.getLoggedInUser().getUserId());
                        break;
                    case "8":
                        MembershipManager.renewMembership(App.getLoggedInUser().getUserId());
                        break;
                    case "9":
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
                        "1. Browse Catalogue\n" +
                        "2. View borrowing history\n" +
                        "3: View Newsletter\n" +
                        "4: View Notifications\n" +
                        "5: Add Book\n" +
                        "6: Remove Book\n" +
                        "7: Update Book Details\n" +
                        "8: View Library Activities\n" +
                        "9: Update NewsLetter\n" +
                        "10: Logout\n" +
                        "******************************************************************************************\n");
                String librarianCommands = scanner.nextLine();
                switch (librarianCommands) {
                    case "1":
                        assetManagement.browse();
                        break;
                    case "2":
                        assetManagement.getBorrowingHistory();
                        break;
                    case "3":
                        // left
                        break;
                    case "4":
                        // left
                        break;
                    case "5":
                        assetManagement.addBook();
                        break;
                    case "6":
                        assetManagement.removeBook();
                        break;
                    case "7":
                         assetManagement.updateBook();
                        break;
                    case "8":
                        // assetManagement.viewLibraryActivities();
                    case "9":
                        // assetManagement.updateNewsLetter();
                    case "10":
                        return;
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
                        "7. Logout\n" +
                        "******************************************************************************************\n");
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
                        roomManagement.manageRoomBooking();
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
                        "5. View Newsletter\n" +
                        "6. Update Newsletter\n" +
                        "7. View All Users\n" +
                        "8. Logout\n" +
                        "******************************************************************************************\n");
                String studentCommands = scanner.nextLine();
                switch (studentCommands) {
                    case "1":
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
                        ViewNews.viewNewsletters();
                        break;
                    case "6":
                        UpdateNews.updateNewsletterProcess();
                        break;
                    case "7":
                        AdminService.viewAllUsers();
                        break;
                    case "8":
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
