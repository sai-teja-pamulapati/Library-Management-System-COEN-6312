package com.university.library.repository;

import java.util.HashMap;
import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;

public class UserRepository {

    private static UserRepository instance;
    private HashMap<String, User> users = new HashMap<>();

    private UserRepository() {
        // Hardcoded sample users
        addUser(new User("Admin", "admin@gmail.com", "admin123", "1234567890", "123 Main St", "1990-01-01", "Male", UserRole.ADMIN));
        addUser(new User("John Smith", "john.smith@gmail.com", "john123", "0987654321", "456 Elm St", "1992-02-02", "Female", UserRole.STAFF));
        addUser(new User("Sam Wilson", "sam.wilson@gmail.com", "sam123", "1122334455", "789 Pine St", "1993-03-03", "Other", UserRole.LIBRARIAN));
    }
    

    public static synchronized UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    private boolean exists(String emailId) {
        return users.containsKey(emailId);
    }

    public boolean addUser(User user) {
        if (exists(user.getEmailId())) {
            return false;
        }
        users.put(user.getEmailId(), user);
        return true;
    }

    public User getUser(String emailId) {
        return users.get(emailId);
    }

}
