package com.university.library.repository;

import java.util.HashMap;
import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;

public class UserRepository {

    private static UserRepository instance;
    private HashMap<String, User> users = new HashMap<>();

    private UserRepository() {
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
