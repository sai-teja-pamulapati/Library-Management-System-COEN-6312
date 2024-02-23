package com.university.library.repository;

import java.util.HashMap;

import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;

public class UserRepository {

    private static UserRepository instance;
    private HashMap<String, User> users = new HashMap<>();

    private UserRepository() {}
    

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

    public boolean removeUser(String emailId) {
        if (users.containsKey(emailId)) {
            users.remove(emailId);
            return true;
        }
        return false;
    }

    public boolean updateBlockedStatus(String emailId, boolean blocked) {
        User user = users.get(emailId);
        if (user != null) {
            user.toggleBlockedStatus();
            return true;
        }
        return false;
    }

    public boolean updateUnblockedStatus(String emailId, boolean blocked) {
        User user = users.get(emailId);
        if (user != null) {
            user.toggleUnblockedStatus();
            return true;
        }
        return false;
    }



    public User getUser(String emailId) {
        return users.get(emailId);
    }

    public void updateUser(User user) {

    }
}
