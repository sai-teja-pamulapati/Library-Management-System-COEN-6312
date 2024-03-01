package com.university.library.model.users.nonacademic;

import com.university.library.model.users.UserRole;

public class FreeUser extends NonAcademic {

    public FreeUser(String userId, String name, String password, String emailId, String mobileNumber,
                    String address, String dateOfBirth, String gender, UserRole userRole, String organisation) {
        
        
        super(userId, name, password, emailId, mobileNumber, address, dateOfBirth, gender, userRole, organisation);
    }
    
    
}
