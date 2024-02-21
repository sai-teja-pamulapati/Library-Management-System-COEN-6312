package com.university.library.model.users.non_academic;

public class PaidUser {
    // Attributes
    private String membershipStartDate;
    private String membershipEndDate;
    private String membershipPeriod;

    // Constructors
    public PaidUser() {
        // Default constructor
    }

    public PaidUser(String membershipStartDate, String membershipEndDate, String membershipPeriod) {
        this.membershipStartDate = membershipStartDate;
        this.membershipEndDate = membershipEndDate;
        this.membershipPeriod = membershipPeriod;
    }

    // Getters and setters
    public String getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate(String membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
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
