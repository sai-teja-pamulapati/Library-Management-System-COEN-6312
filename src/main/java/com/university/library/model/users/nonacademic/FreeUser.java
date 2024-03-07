package com.university.library.model.users.nonacademic;

public class FreeUser extends NonAcademicUser {

    public FreeUser(String userId , String name , String password , String emailId , String mobileNumber , String address , String dateOfBirth , String gender , String organisation) {
        super(userId , name , password , emailId , mobileNumber , address , dateOfBirth , gender , organisation);
    }
    @Override
    public String toString() {
        return getClass().getName() +"\n"+ super.toString();
    }
}
