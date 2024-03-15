package com.university.library.model.users.academic;

import com.university.library.model.users.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AcademicUser extends User {

    private String universityId;
    private String issueDate;

    public AcademicUser(String userId , String name , String emailId , String password , String mobileNumber ,
    String address , String dateOfBirth , String gender, String universityId , String issueDate) {
        super(userId, name , emailId , password , mobileNumber , address , dateOfBirth , gender);
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

    public boolean isIssueDateValid() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        try {
            Date issueDate = dateFormat.parse(this.issueDate);
            return !issueDate.after(today);
        } catch (ParseException e) {
            System.err.println("Error parsing issueDate: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "universityId: " + universityId + '\n' +
                "issueDate: " + issueDate;
    }

}
