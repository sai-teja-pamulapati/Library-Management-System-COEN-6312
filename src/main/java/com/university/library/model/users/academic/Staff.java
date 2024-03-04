package com.university.library.model.users.academic;

import com.university.library.model.users.UserRole;

public class Staff extends AcademicUser {

    private String department;

    public Staff(String name , String emailId , String password , String mobileNumber , String address , String dateOfBirth , String gender , UserRole userRole , String universityId , String issueDate , String department) {
        super(name , emailId , password , mobileNumber , address , dateOfBirth , gender , userRole , universityId , issueDate);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
