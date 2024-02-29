package com.university.library.model.users.academic;

import com.university.library.model.users.UserRole;

public class Admin extends Academic {
    
    private String officeLocation;
    private String officeHours;
    private String contractType;

    public Admin(String userId, String name, String password, String emailId, String mobileNumber,
                 String address, String dateOfBirth, String gender, UserRole userRole,
                 String university_id, String idIssueDate, String officeLocation,
                 String officeHours, String contractType) {
        // Call to Academic constructor
        super(userId, name, password, emailId, mobileNumber, address, dateOfBirth, gender, userRole, university_id, idIssueDate);
        
        // Initialize Admin-specific fields
        this.officeLocation = officeLocation;
        this.officeHours = officeHours;
        this.contractType = contractType;
    }

    // Getters and Setters for Admin-specific fields
    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }
}
 