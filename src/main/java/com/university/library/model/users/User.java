package com.university.library.model.users;

import com.university.library.repository.UserRepository;

public class User {
    private String name;
    private String password;
    private String emailId;
    private String mobileNumber;
    private String address;
    private UserRole userRole;
    private String dateOfBirth;
    private String gender;

    private static UserRepository userRepository = UserRepository.getInstance();

    public User addUser() {
        if (userRepository.addUser(this)) {
            return this;
        }
        return null;
    }

    public User() {}

    public User(String name, String emailId, String password, String mobileNumber, 
                String address, String dateOfBirth, String gender, UserRole userRole) {
        this.name = name;
        this.emailId = emailId;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.userRole = userRole;
    }

    public static User login(String emailId, String password, UserRepository userRepository) {
        User user = userRepository.getUser(emailId);
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("Invalid Credentials!!");
            return null;
        }
        System.out.println("Login Successful!");
        return user;
    }

    public static boolean register(String name, String emailId, String password, String mobileNumber,
                                   String address, String dateOfBirth, String gender, UserRole userRole,
                                   UserRepository userRepository) {
        if (userRepository.getUser(emailId) != null) {
            System.out.println("Registration failed. User with this email already exists.");
            return false;
        }
        
        User newUser = new User(name, emailId, password, mobileNumber, address, dateOfBirth, gender, userRole);
        
        if (userRepository.addUser(newUser)) {
            System.out.println("User registered successfully!");
            return true;
        } else {
            System.out.println("Registration failed.");
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}