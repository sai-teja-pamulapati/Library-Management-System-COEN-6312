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
            Pattern.compile("^[A-Z0-9]+@[A-Z0-9]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static void register(boolean selfRegistration) {

        Console console = System.console();

        String name = getInputFromUser(selfRegistration, "Please enter your name:", "Please enter name of user:");

        String emailId;
        while(true) {
            emailId = getInputFromUser(selfRegistration, "Please enter your email ID:", "Please enter user's email ID:");
            if (validateEmailId(emailId)){
                break;
            }
            System.out.println("Wrong email format!! Please enter the email id in this format - xyz@abc.com");
        }

        String password = new String(console.readPassword());

        String mobileNumber = getInputFromUser(selfRegistration, "Please enter your mobile number", "Please enter user's mobile number");

        String address = getInputFromUser(selfRegistration, "Please enter your address", "Please enter user's address");

        String dateOfBirth = getInputFromUser(selfRegistration, "Please enter your date of birth (YYYY-MM-DD)", "Please enter user's date of birth (YYYY-MM-DD)");

        String gender = "";
        while(true) {
            System.out.print("Please select the gender:\n" +
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

    private static String getInputFromUser(boolean selfRegistration , String selfPrompt , String userPrompt) {
        String prompt = selfRegistration ? selfPrompt : userPrompt;
        System.out.println(prompt);
        return scanner.nextLine();
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
                newUser = getAdminUser(name , emailId , password , mobileNumber , address , dateOfBirth , gender, selfRegistration);
                break;
            case STUDENT:
                newUser = getStudentUser(name , emailId , password , mobileNumber , address , dateOfBirth , gender, selfRegistration);
                break;
            case STAFF:
                newUser = getStaffUser(name , emailId , password , mobileNumber , address , dateOfBirth , gender, selfRegistration);
                break;
            case LIBRARIAN:
                newUser = getLibrarianUser(name , emailId , password , mobileNumber , address , dateOfBirth , gender, selfRegistration);
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

    private static User getLibrarianUser(String name , String emailId , String password , String mobileNumber , String address , String dateOfBirth , String gender , boolean selfRegistration) {
        String universityId = getInputFromUser(selfRegistration, "Please enter your university id", "Please enter user's university id");
        String department = getInputFromUser(selfRegistration, "Please enter your department", "Please enter user's department");
        return new Librarian(null, name , emailId , password , mobileNumber , address , dateOfBirth , gender , universityId, department);
    }

    private static User getStaffUser(String name , String emailId , String password , String mobileNumber , String address , String dateOfBirth , String gender , boolean selfRegistration) {
        String universityId = getInputFromUser(selfRegistration, "Please enter your university id", "Please enter user's university id");
        String department = getInputFromUser(selfRegistration, "Please enter your department", "Please enter user's department");
        return new Staff(null, name , emailId , password , mobileNumber , address , dateOfBirth , gender , universityId, null, department);
    }

    private static User getStudentUser(String name , String emailId , String password , String mobileNumber , String address , String dateOfBirth , String gender , boolean selfRegistration) {
        String universityId = getInputFromUser(selfRegistration, "Please enter your university id", "Please enter user's university id");
        String department = getInputFromUser(selfRegistration, "Please enter your department", "Please enter user's department");
        return new Student(null, name , emailId , password , mobileNumber , address , dateOfBirth , gender , universityId, null, department);
    }

    private static User getAdminUser(String name , String emailId , String password , String mobileNumber , String address , String dateOfBirth , String gender , boolean selfRegistration) {
        String universityId = getInputFromUser(selfRegistration, "Please enter your university id", "Please enter user's university id");
        String officeHours = getInputFromUser(selfRegistration, "Please enter your office hours", "Please enter user's office hours");
        String officeLocation = getInputFromUser(selfRegistration, "Please enter your office location", "Please enter user's office location");
        return new Admin(null , name , emailId , password , mobileNumber , address , dateOfBirth , gender , universityId , null , officeLocation , officeHours , null);
    }
}
