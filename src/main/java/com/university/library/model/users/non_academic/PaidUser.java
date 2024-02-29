package com.university.library.model.users.non_academic;

public class PaidUser {
    // Attributes
    private String membershipIssueDate;
    private String membershipEndDate;
    private String membershipPeriod;

    // Constructors
    public PaidUser() {
        // Default constructor
    }

    public PaidUser(String membershipIssueDate, String membershipEndDate, String membershipPeriod) {
        this.membershipIssueDate = membershipIssueDate;
        this.membershipEndDate = membershipEndDate;
        this.membershipPeriod = membershipPeriod;
    }

    // Getters and setters
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
