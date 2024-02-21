package com.university.library.model.users;


public class User {
    private String name;
    private String password;
    private String emailId;
    private String mobileNumber;
    private String address;
    private UserRole userRole;
    private String dateOfBirth;
    private String gender;

    
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
        // TODO code
        User user = new User();
        user.setUserRole(UserRole.STUDENT);
        return user;
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
