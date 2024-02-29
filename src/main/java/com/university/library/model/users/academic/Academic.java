package com.university.library.model.users.academic;

import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;

public class Academic extends User {
    
    private String university_id;
    private String Id_Issuedate;

 public Academic(String userId, String name, String password, String emailId, String mobileNumber,
                String address, String dateOfBirth, String gender, UserRole userRole,
                String university_id, String Id_Issuedate) {
    super(name, emailId, password, mobileNumber, address, dateOfBirth, gender, userRole);
    setUserId(userId); 
    this.university_id = university_id;
    this.Id_Issuedate = Id_Issuedate;
}

  
    public String getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(String university_id) {
        this.university_id = university_id;
    }

    public String getId_Issuedate() {
        return Id_Issuedate;
    }

    public void setId_Issuedate(String Id_Issuedate) {
        this.Id_Issuedate = Id_Issuedate;
    }
}
