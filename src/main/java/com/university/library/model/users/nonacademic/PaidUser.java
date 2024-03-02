package com.university.library.model.users.nonacademic;
//import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;


public class PaidUser extends NonAcademic {
    
    private String membershipIssueDate;
    private String membershipEndDate;
    private String membershipPeriod;

    public PaidUser(String userId, String name, String password, String emailId, String mobileNumber,
                    String address, String dateOfBirth, String gender, UserRole userRole, String organisation,
                    String membershipIssueDate, String membershipEndDate, String membershipPeriod) {
        
        super(userId, name, password, emailId, mobileNumber, address, dateOfBirth, gender, userRole, organisation);
        
     
        this.membershipIssueDate = membershipIssueDate;
        this.membershipEndDate = membershipEndDate;
        this.membershipPeriod = membershipPeriod;
    }

    
    public String getMembershipIssueDate() {
        return membershipIssueDate;
    }

    public void setMembershipIssueDate(String membershipIssueDate) {
        this.membershipIssueDate = membershipIssueDate;
    }

    public String getMembershipEndDate() {
        return membershipEndDate;
    }

    public void setMembershipEndDate(String membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    public String getMembershipPeriod() {
        return membershipPeriod;
    }

    public void setMembershipPeriod(String membershipPeriod) {
        this.membershipPeriod = membershipPeriod;
    }
}
