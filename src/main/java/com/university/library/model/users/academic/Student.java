package com.university.library.model.users.academic;

public class Student extends Academic {
    // Additional properties specific to students
    private String department;

    // Constructors
    public Student() {
        // Default constructor
    }

    public Student(String department) {
        this.department = department;
    }

    // Getter and setter for department
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    // Other methods specific to students
}
