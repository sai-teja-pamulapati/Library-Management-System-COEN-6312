package com.university.library.model.users.academic;

public class Librarian extends AcademicUser {
    private String officeLocation;

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

    private String officeHours;
    private String contractType;

    public Librarian(String userId , String name , String emailId , String password , String mobileNumber , String address , String dateOfBirth , String gender , String universityId , String issueDate) {
        super(userId, name , emailId , password , mobileNumber , address , dateOfBirth , gender , universityId , issueDate);
    }

    @Override
    public String toString() {
        return getClass().getName() +"\n"+ super.toString();
    }
}
