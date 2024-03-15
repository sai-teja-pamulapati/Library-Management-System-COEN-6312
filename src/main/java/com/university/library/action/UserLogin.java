package com.university.library.action;

import com.university.library.App;
import com.university.library.model.users.User;
import com.university.library.model.users.academic.Admin;
import com.university.library.model.users.academic.Librarian;
import com.university.library.model.users.academic.Staff;
import com.university.library.model.users.academic.Student;
import com.university.library.model.users.nonacademic.FreeUser;
import com.university.library.model.users.nonacademic.PaidUser;

import java.io.Console;
import java.util.Scanner;

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
        if (user instanceof Admin) {
            processAdminUser();
        } else if (user instanceof Student) {
            processStudentUser();
        } else if (user instanceof Staff) {
            processStaffUser();
        } else if (user instanceof Librarian) {
            processLibrarianUser();
        } else if (user instanceof FreeUser) {
            processFreeUser();
        } else if (user instanceof PaidUser) {
            processPaidUser();
        } else {
            System.out.println("Invalid user role.");
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
                        assetManagement.getBorrowingHistory();
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
                        break;
                    case "4":
                        showNotifications();
                        break;
                    case "5":
                        payFines();
                        break;
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
                        return;
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
                        "10. Book/Cancel Presentation Room\n" +
                        "11: Block User\n" +
                        "12: Unblock User\n" +
                        "13: Logout\n" +
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
                        ViewNews.viewNewsletters();
                        break;
                    case "4":
                        showNotifications();
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

                        AdminService.viewAllUsers();
                        break;
                    case "9":
                        UpdateNews.updateNewsletterProcess();

                        // assetManagement.viewLibraryActivities();
                       
                    

                        break;
                    case "10":
                        roomManagement.manageRoomBooking();
                        break;
                    case "11":
                        UserBlocking.blockUser(App.getLoggedInUser());
                        break;
                    case "12":
                        UserBlocking.unblockUser(App.getLoggedInUser());
                        break;
                    case "13":
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
        while (true) {
            try {
                System.out.print("Choose from the following options\n" +
                        "1. Browse Catalogue\n" +
                        "2. View borrowing history\n" +
                        "3. View Newsletter\n" +
                        "4. View Notifications\n" +
                        "5. Book/Cancel Presentation Room\n" +
                        "6. Pay Fines\n" +
                        "7. Logout\n" +
                        "******************************************************************************************\n");
                String staffCommands = scanner.nextLine();
                switch (staffCommands) {
                    case "1":
                        assetManagement.browse();
                        break;
                    case "2":
                        assetManagement.getBorrowingHistory();
                        break;
                    case "3":
                        ViewNews.viewNewsletters();
                        break;
                    case "4":
                        showNotifications();
                        break;
                    case "5":
                        roomManagement.manageRoomBooking();
                        break;
                    case "6":
                        payFines();
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

    private static void processStudentUser() {
        while (true) {
            try {
                System.out.print("Choose from the following options\n" +
                        "1. Browse Catalogue\n" +
                        "2. View borrowing history\n" +
                        "3. View Newsletter\n" +
                        "4. View Notifications\n" +
                        "5. Book/Cancel Presentation Room\n" +
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
                        ViewNews.viewNewsletters();
                        break;
                    case "4":
                        showNotifications();
                        break;
                    case "5":
                        roomManagement.manageRoomBooking();
                        break;
                    case "6":
                        payFines();
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

    private static void payFines() {
        System.out.println("You have successfully paid the fines");
    }

    private static void showNotifications() {
        System.out.println("Your notifications will appear here");
    }

    private static void processAdminUser() {
        while (true) {
            try {
                System.out.print("Choose from the following options\n" +
                        "1. Browse Catalogue\n" +
                        "2. View borrowing history\n" +
                        "3. View Notifications\n" +
                        "4. View Newsletter\n" +
                        "5. Add Newsletter\n" +
                        "6. Update Newsletter\n" +
                        "7. Book/Cancel Presentation Room\n" +
                        "8. Add User\n" +
                        "9. Remove User\n" +
                        "10. Block User\n" +
                        "11. Unblock User\n" +
                        "12. View All Users\n" +
                        "13. Pay Fines\n" +
                        "14. Logout\n" +
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
                        showNotifications();
                        break;
                    case "4":
                        ViewNews.viewNewsletters();
                        break;
                    case "5":
                        assetManagement.addNewsLetter();
                        break;
                    case "6":
                        UpdateNews.updateNewsletterProcess();
                        break;
                    case "7":
                        roomManagement.manageRoomBooking();
                        break;
                    case "8":
                        UserRegistration.register(false);
                        break;
                    case "9":
                        UserRemoval.removeUser();
                        break;
                    case "10":
                        UserBlocking.blockUser(App.getLoggedInUser());
                        break;
                    case "11":
                        UserBlocking.unblockUser(App.getLoggedInUser());
                        break;
                    case "12":
                        AdminService.viewAllUsers();
                        break;
                    case "13":
                        payFines();
                        break;
                    case "14":
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
