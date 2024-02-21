package com.university.library.model.users.academic;

public class Staff extends Academic {
    // Additional properties specific to staff
    private String department;

    // Constructors
    public Staff() {
        // Default constructor
    }

    public Staff(String department) {
        this.department = department;
    }

    // Getter and setter for department
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    // Other methods specific to staff
}
