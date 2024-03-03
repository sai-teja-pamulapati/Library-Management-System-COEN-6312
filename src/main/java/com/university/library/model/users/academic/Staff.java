package com.university.library.model.users.academic;

import com.university.library.model.users.UserRole;

public class Staff extends AcademicUser {

    private String department;

    public Staff(String userId, String name, String password, String emailId, String mobileNumber,
                 String address, String dateOfBirth, String gender, UserRole userRole,
                 String university_id, String idIssueDate, String department) {
        
        super(userId, name, password, emailId, mobileNumber, address, dateOfBirth, gender, userRole, university_id, idIssueDate);
        
        
        this.department = department;
    }

   
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
