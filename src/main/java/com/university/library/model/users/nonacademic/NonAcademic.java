package com.university.library.model.users.nonacademic;

import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;

public class NonAcademic extends User {

    private String organisation;

    public NonAcademic(String userId, String name, String password, String emailId, String mobileNumber,
            String address, String dateOfBirth, String gender, UserRole userRole, String organisation) {
        super(name, emailId, password, mobileNumber, address, dateOfBirth, gender, userRole);
        setUserId(userId);
        this.organisation = organisation;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

}
