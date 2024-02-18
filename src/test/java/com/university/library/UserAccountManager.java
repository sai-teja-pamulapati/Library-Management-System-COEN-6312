import java.util.HashMap;
import java.util.Map;

public class UserAccountManager {
    private Map<String, User> userAccounts = new HashMap<>();

    public UserAccountManager() {
        userAccounts.put("admin", new User("admin", "admin123", User.UserRole.ADMIN));
    }

    public boolean createUserAccount(String username, String password, User.UserRole role) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            System.out.println("Username and password cannot be empty");
            return false;
        }
        if (userAccounts.containsKey(username)) {
            System.out.println("User already exists");
            
            return false;
        }

        if (password.length() < 8) {
            System.out.println("Password must be at least 8 characters long");
            return false;
        }

        User newUser = new User(username, password, role);
        userAccounts.put(username, newUser);
        System.out.println("User account created successfully for username: " + username + " Role: " + role);
        return true;
    }

    public boolean doesUsernameExist(String username) {
        return userAccounts.containsKey(username);
    }

    public boolean verifyLogin(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        User user = userAccounts.get(username);
        return user != null && user.getPassword().equals(password);
    }
}
