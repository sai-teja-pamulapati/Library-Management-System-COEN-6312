package com.university.library.model.users.academic;

import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;

public class AcademicUser extends User {
    
    private String universityId;
    private String issueDate;

    public AcademicUser(String name , String emailId , String password , String mobileNumber , String address , String dateOfBirth , String gender , UserRole userRole , String universityId , String issueDate) {
        super(name , emailId , password , mobileNumber , address , dateOfBirth , gender , userRole);
        this.universityId = universityId;
        this.issueDate = issueDate;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
}
