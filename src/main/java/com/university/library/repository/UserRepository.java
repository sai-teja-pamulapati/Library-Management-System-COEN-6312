package com.university.library.repository;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;

public class UserRepository {

    private static final AtomicInteger assetIdGenerator = new AtomicInteger(0);

    private static UserRepository instance;
    private static HashMap<String, User> users = new HashMap<>();

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
        user.setUserId(String.valueOf(assetIdGenerator.getAndIncrement()));
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


    public User getUser(String emailId) {
        return users.get(emailId);
    }

    public void updateUser(User user) {
        if (exists(user.getEmailId())) {
            users.put(user.getEmailId(), user);
        }
    }

}
