package com.university.library.repository;

import com.university.library.App;
import com.university.library.model.users.User;
import com.university.library.model.users.academic.Admin;
import com.university.library.model.users.academic.Librarian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepository {

    private static final AtomicInteger assetIdGenerator = new AtomicInteger(0);
    private static UserRepository instance;
    private static HashMap<String, User> users = new HashMap<>();

    public UserRepository() {
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
            System.out.println("Account already exists for the email id");
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

    public void clearUsers() {
        users.clear();
        assetIdGenerator.set(0);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User getUser(String emailId) {
        return users.get(emailId);
    }

    public void updateUser(User user) {
        if (exists(user.getEmailId())) {
            users.put(user.getEmailId(), user);
        }
    }

    public boolean isAdmin(String emailId) {
        User user = getUser(emailId);
        return user instanceof Admin;
    }

    public static void updateOfficehours() {
        User loggedInUser = App.getLoggedInUser();
        Scanner scanner = new Scanner(System.in);
        if (loggedInUser instanceof Admin || loggedInUser instanceof Librarian) {
            if (loggedInUser instanceof Admin) {
                Admin adminUser = (Admin) loggedInUser;
                System.out.println("Enter new office hourse for admin");
                String newOfficeHours = scanner.nextLine();
                if (isValidOfficeHours(newOfficeHours)) {
                    adminUser.updateOfficehours(newOfficeHours);
                    UserRepository.getInstance().updateUser(adminUser);
                    System.out.println("Office hours updated");
                } else {
                    System.out.println(
                            "Invalid officehours format  or office hours exceeded. Please note office hours is between 08:00 to 17:00 only.Login again to update office hours");
                }

            } else if (loggedInUser instanceof Librarian) {
                Librarian librarianUser = (Librarian) loggedInUser;
                System.out.println("Enter new office hourse for librarian");
                String newOfficeHours = scanner.nextLine();
                if (isValidOfficeHours(newOfficeHours)) {
                    librarianUser.updateOfficehours(newOfficeHours);
                    UserRepository.getInstance().updateUser(librarianUser);
                    System.out.println("Office hours updated");
                } else {
                    System.out.println(
                            "Invalid officehours format or office hours exceeded .Please note office hours is between 08:00 to 17:00 only.Login again to update office hours ");
                }

            }

        } else {
            System.out.println(
                    "You dont have permission to update office hours.");
        }
    }

    private static boolean isValidOfficeHours(String officeHours) {
        if (!officeHours.matches("\\d{2}:\\d{2}-\\d{2}:\\d{2}")) {
            return false;

        }
        String[] hours = officeHours.split("-");
        String startHour = hours[0];
        String endHour = hours[1];
        if (startHour.compareTo(endHour) >= 0) {
            System.out.println("Please note office hours is between 08:00 to 17:00 only");

            return false;

        }
        if (startHour.compareTo("08:00") < 0 || endHour.compareTo("17:00") > 0) {
            System.out.println("Please note office hours is between 08:00 to 17:00 only");
            return false;

        }
        return true;
    }

}
