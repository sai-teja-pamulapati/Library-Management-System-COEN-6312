package com.university.library.model.users.academic;

import com.university.library.model.users.UserRole;

public class Librarian extends Academic {
    
    private String supervisor;

    public Librarian(String userId, String name, String password, String emailId, String mobileNumber,
                     String address, String dateOfBirth, String gender, UserRole userRole,
                     String university_id, String idIssueDate, String supervisor) {
        
        super(userId, name, password, emailId, mobileNumber, address, dateOfBirth, gender, userRole, university_id, idIssueDate);
        
        
        this.supervisor = supervisor;
    }

    
    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
}
