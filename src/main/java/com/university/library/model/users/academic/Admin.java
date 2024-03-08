package com.university.library.model.users.academic;

public class Admin extends AcademicUser {

    private String officeLocation;
    private String officeHours;
    private String contractType;

    public Admin(String userId, String name , String emailId , String password , String mobileNumber , String address , String dateOfBirth , String gender , String universityId , String issueDate , String officeLocation , String officeHours , String contractType) {
        super(userId, name , emailId , password , mobileNumber , address , dateOfBirth , gender , universityId , issueDate);
        this.officeLocation = officeLocation;
        this.officeHours = officeHours;
        this.contractType = contractType;
    }

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

    @Override
    public String toString() {
        return getClass().getName() +"\n"+ super.toString();
    }
}