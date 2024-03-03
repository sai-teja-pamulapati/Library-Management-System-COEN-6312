package com.university.library.model.users.nonacademic;

//import com.university.library.action.MembershipManagement;
import com.university.library.action.MembershipManager;
import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;
import java.util.Date;

public class NonAcademic extends User {

    private String organisation;

    public NonAcademic(String userId, String name, String password, String emailId, String mobileNumber,
            String address, String dateOfBirth, String gender, UserRole userRole, String organisation) {
        super(name, emailId, password, mobileNumber, address, dateOfBirth, gender, userRole);
        setUserId(userId);
        this.organisation = organisation;
        // intial condition

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
