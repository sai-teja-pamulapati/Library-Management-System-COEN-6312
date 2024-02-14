import java.util.Scanner;

public class AccCreation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserAccountManager accountManager = new UserAccountManager();

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        boolean isCreated = false;
        while (!isCreated) {
            System.out.println("Enter password (password must be at least 8 characters long):");
            String password = scanner.nextLine();

            isCreated = accountManager.createUserAccount(username, password);
            if (!isCreated) {
                System.out.println("Password did not meet the criteria. Please try again.");
            }
        }

        System.out.println("Account creation successful.");
        scanner.close(); // Close the scanner to prevent resource leaks
    }
}
