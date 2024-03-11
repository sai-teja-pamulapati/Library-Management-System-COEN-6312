package com.university.library.action;

import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;
import com.university.library.model.users.academic.Admin;
import com.university.library.model.users.academic.Librarian;
import com.university.library.model.users.academic.Staff;
import com.university.library.model.users.academic.Student;
import com.university.library.model.users.nonacademic.FreeUser;
import com.university.library.model.users.nonacademic.PaidUser;
import com.university.library.repository.UserRepository;

import java.io.Console;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegistration {

    private static Scanner scanner = new Scanner(System.in);
    private static UserRepository userRepository = UserRepository.getInstance();

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static void register(boolean selfRegistration) {

        Console console = System.console();

        String prompt = selfRegistration ? "Please enter your name:" : "Please enter name of user:";
        System.out.println(prompt);
        String name = scanner.nextLine();

        String emailId = getEmailId(selfRegistration);

        prompt = selfRegistration ? "Please enter your password" : "Please enter user's desired password";
        System.out.println(prompt);
        String password = new String(console.readPassword());

        prompt = selfRegistration ? "Please enter your mobile number" : "Please enter user's mobile number";
        System.out.println(prompt);
        String mobileNumber = scanner.nextLine();

        prompt = selfRegistration ? "Please enter your address" : "Please enter user's address";
        System.out.println(prompt);
        String address = scanner.nextLine();

        prompt = selfRegistration ? "Please enter your date of birth (YYYY-MM-DD)" : "Please enter user's date of birth (YYYY-MM-DD)";
        System.out.println(prompt);
        String dateOfBirth = scanner.nextLine();

        String gender = "";
        while(true) {
            System.out.print("Please select your gender:\n" +
                "1. Male\n" +
                "2. Female\n" +
                "3. Other\n");
            String genderOption = scanner.nextLine();
            switch (genderOption) {
                case "1":
                    gender = "Male";
                    break;
                case "2":
                    gender = "Female";
                    break;
                case "3":
                    gender = "Other";
                    break;
                default:
                    System.out.println("Invalid option!");
                    continue;
                }
            break;
        }

        User newUser = getUserObject(name , emailId , password , mobileNumber , address , dateOfBirth , gender, selfRegistration);

        boolean added = userRepository.addUser(newUser);
        if (added) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("User registration failed. Email ID already exists.");
        }
    }

    private static String getEmailId(boolean selfRegistration) {
        String prompt = selfRegistration ? "Please enter your email ID:" : "Please enter user's email ID:";
        System.out.println(prompt);
        while(true) {
            String emailId = scanner.nextLine();
            if (validateEmailId(emailId)){
                return emailId;
            }
            System.out.println("Wrong email format!! Please enter the email id in this format - xyz@abc.com");
        }
    }

    public static boolean validateEmailId(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    private static User getUserObject(String name , String emailId , String password , String mobileNumber , String address , String dateOfBirth , String gender , boolean selfRegistration) {
        UserRole userRole;
        while (true) {
            System.out.println("Please select your role:");
            UserRole[] roles;
            if (selfRegistration){
                roles = new UserRole[] {UserRole.FREE_USER, UserRole.PAID_USER, UserRole.STUDENT, UserRole.STAFF};
            } else {
                roles = UserRole.values();
            }
            for (int i = 0; i < roles.length; i++) {
                System.out.println((i + 1) + ". " + roles[i].name());
            }
            try {
                int roleOption = Integer.parseInt(scanner.nextLine()) - 1;
                if (roleOption >= 0 && roleOption < roles.length) {
                    userRole = roles[roleOption];
                } else {
                    System.out.println("Invalid selection. Please try again.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            break;
        }
        User newUser = null;

        switch (userRole) {
            case ADMIN:
                newUser = new Admin(null, name , emailId , password , mobileNumber , address , dateOfBirth , gender , null, null, null, null, null);
                break;
            case STUDENT:
                newUser = new Student(null, name , emailId , password , mobileNumber , address , dateOfBirth , gender , null, null, null);
                break;
            case STAFF:
                newUser = new Staff(null, name , emailId , password , mobileNumber , address , dateOfBirth , gender , null, null, null);
                break;
            case LIBRARIAN:
                newUser = new Librarian(null, name , emailId , password , mobileNumber , address , dateOfBirth , gender , null, null);
                break;
            case FREE_USER:
                newUser = new FreeUser(null, name , emailId , password , mobileNumber , address , dateOfBirth , gender , null);
                break;
            case PAID_USER:
                newUser = new PaidUser(null, name , emailId , password , mobileNumber , address , dateOfBirth , gender , null);
                break;
        }
        return newUser;
    }
}
