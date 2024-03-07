package com.university.library.model.users.nonacademic;

import com.university.library.action.MembershipManager;
import com.university.library.model.users.User;

import java.util.Date;

public class NonAcademicUser extends User {

    private String organisation;

    public NonAcademicUser(String userId , String name , String password , String emailId , String mobileNumber ,
                           String address , String dateOfBirth , String gender , String organisation) {
        super(userId, name, emailId, password, mobileNumber, address, dateOfBirth, gender);
        this.organisation = organisation;
    }

    public void buyMemberhsip(double amount, Date startDate, Date endDate) {
        MembershipManager.buyMembership();
    }

    public void renewMemberhsip(String userId) {
        MembershipManager.renewMembership(userId);
    }

    public void cancelMemberhsip(String userId) {
        MembershipManager.cancelMembership(userId);
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "organisation: " + getOrganisation();
    }
}
