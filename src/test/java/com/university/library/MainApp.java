import java.util.Scanner;

public class MainApp {
    private static UserAccountManager accountManager = new UserAccountManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Library Management System");

        while (true) {
            System.out.println("Do you want to Login or Create Account? (Login/Create/Exit):");
            String action = scanner.nextLine();

            if ("Login".equalsIgnoreCase(action)) {
                login();
            } else if ("Create".equalsIgnoreCase(action)) {
                createAccount();
            } else if ("Exit".equalsIgnoreCase(action)) {
                break;
            } else {
                System.out.println("Invalid option, please choose Login, Create, or Exit.");
            }
        }

        scanner.close(); // Close the scanner to prevent resource leaks
    }

    private static void login() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();

        if (!accountManager.doesUsernameExist(username)) {
            System.out.println("Username does not exist. Redirecting to account creation.");
            createAccount();
            return;
        }

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (accountManager.verifyLogin(username, password)) {
            System.out.println("Login successful. Welcome back, " + username + "!");
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private static void createAccount() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();

        boolean isCreated = false;
        while (!isCreated) {
            System.out.println("Enter password (password must be at least 8 characters long):");
            String password = scanner.nextLine();

            isCreated = accountManager.createUserAccount(username, password);
            if (!isCreated) {
                System.out.println("Failed to create account. Please try again.");
            }
        }

        System.out.println("Account creation successful.");
    }
}
