package com.university.library.model.users.nonacademic;

import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;
import java.util.Date;

public class NonAcademic extends User {

    private String organisation;
    private boolean membershipActive;
    private double membershipAmountPaid;
    private Date membershipStartDate;
    private Date membershipEndDate;

    public NonAcademic(String userId, String name, String password, String emailId, String mobileNumber,
            String address, String dateOfBirth, String gender, UserRole userRole, String organisation) {
        super(name, emailId, password, mobileNumber, address, dateOfBirth, gender, userRole);
        setUserId(userId);
        this.organisation = organisation;
        // intial condition
        this.membershipActive = false;
        this.membershipAmountPaid = 0;
        this.membershipEndDate = null;
        this.membershipStartDate = null;
    }

    public void buyMemberhsip(double amount, Date startDate, Date endDate) {
        if (!membershipActive) {
            this.membershipActive = true;
            this.membershipAmountPaid = amount;
            this.membershipStartDate = startDate;
            this.membershipStartDate = endDate;
            System.out.println("Membership purchase successfully");
        } else {
            System.out.println("you are already a active membership holder");

        }
    }

    public void renewMembership(double amount, Date endDate) {
        if (membershipActive) {
            this.membershipAmountPaid += amount;
            this.membershipEndDate = endDate;
            System.out.println("Membership renewed successfully");
        } else {
            System.out.println("You dont have active memberhsip to renew");
        }
    }

    public void cancelMembership() {
        if (membershipActive) {
            this.membershipActive = false;
            this.membershipAmountPaid = 0.0;
            this.membershipStartDate = null;
            this.membershipEndDate = null;
            System.out.println("Memberhsip cancelled successfully");
        } else {
            System.out.println("you dont have active membership to cancel");

        }

    }

    public boolean isMembershipActive() {
        return membershipActive;
    }

    public void setMembershipActive(boolean membershipActive) {
        this.membershipActive = membershipActive;
    }

    public double getMembershipAmountPaid() {
        return membershipAmountPaid;
    }

    public void setMembershipAmountPaid(double membershipAmountPaid) {
        this.membershipAmountPaid = membershipAmountPaid;
    }

    public Date getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate(Date membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public Date getMembershipEndDate() {
        return membershipEndDate;
    }

    public void setMembershipEndDate(Date membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    @Override
    public String toString() {
        return "NonAcademic{" +
                "title : " + getUserId() + '\n' +
                "name : " + getName() + '\n' +
                "emailId : " + getEmailId() + '\n' +
                "mobileNumber : " + getMobileNumber() + '\n' +
                "address: " + getAddress() + '\n' +
                "DateofBirth : " + getDateOfBirth() + '\n' +
                "gender : " + getGender() + '\n' +
                "organisation : " + getOrganisation() + '\n' +
                "}";
    }
}
