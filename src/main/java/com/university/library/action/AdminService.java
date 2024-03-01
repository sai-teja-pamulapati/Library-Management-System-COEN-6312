package com.university.library.action;
import com.university.library.model.users.User;
import com.university.library.repository.UserRepository;
import java.util.Comparator;
import java.util.List;

public class AdminService {

    public static void viewAllUsers() {
        List<User> users = UserRepository.getInstance().getAllUsers();

        // Sort users by UserID
        users.sort(Comparator.comparingInt(user -> Integer.parseInt(user.getUserId())));

        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User user : users) {
                System.out.println(user);

            }
        }
    }
}
