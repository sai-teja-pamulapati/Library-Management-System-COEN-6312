package com.university.library.model.users;

import com.university.library.repository.UserRepository;

public class User {

    private String userId;
    private String name;
    private String password;
    private String emailId;
    private String mobileNumber;
    private String address;
    private UserRole userRole;
    private String dateOfBirth;
    private String gender;
    private boolean blocked;

    private static UserRepository userRepository = UserRepository.getInstance();

    public User addUser(boolean printMessage) {
        if (userRepository.addUser(this)) {
            if (printMessage) {
                System.out.println("User registered successfully!");
            }
            return this;
        }
        System.out.println("Registration failed.");
        return null;
    }

    public User addUser() {
        return addUser(true);
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void blockUser() {
        this.blocked = true;
        userRepository.updateUser(this);
    }

    public void unBlockUser() {
        this.blocked = false;
        userRepository.updateUser(this);
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

    public static User login(String emailId , String password) {
        User user = userRepository.getUser(emailId);
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("Invalid Credentials!!");
            return null;
        }
        System.out.println("Login Successful!");
        return user;
    }

    public static User register(User newUser) {
        return newUser.addUser();
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
public String toString() {
    return "UserID: " + getUserId() +
            ", Name: " + getName() +
            ", Email: " + getEmailId() +
            ", Mobile: " + getMobileNumber() +
            ", Role: " + getUserRole() +
            ", Blocked: " + (isBlocked() ? "Yes" : "No");
}

}